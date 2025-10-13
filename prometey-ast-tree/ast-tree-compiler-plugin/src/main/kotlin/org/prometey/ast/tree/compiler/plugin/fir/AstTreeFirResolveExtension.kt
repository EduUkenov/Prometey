package org.prometey.ast.tree.compiler.plugin.fir

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.extensions.ExperimentalTopLevelDeclarationsGenerationApi
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.MemberGenerationContext
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.plugin.createDefaultPrivateConstructor
import org.jetbrains.kotlin.fir.plugin.createMemberProperty
import org.jetbrains.kotlin.fir.plugin.createTopLevelClass
import org.jetbrains.kotlin.fir.symbols.SymbolInternals
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.SpecialNames
import org.prometey.ast.tree.compiler.plugin.AstTreeGeneratedKey
import org.prometey.ast.tree.compiler.plugin.FirAstTreePredicates

@OptIn(SymbolInternals::class)
class AstTreeResolveExtension(
    session: FirSession
) : FirDeclarationGenerationExtension(session) {

    val target by lazy {
        session.predicateBasedProvider.getSymbolsByPredicate(FirAstTreePredicates.annotatedWithAstTreeLookup)
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    override fun generateTopLevelClassLikeDeclaration(classId: ClassId): FirClassLikeSymbol<*>? {
        return createTopLevelClass(
            classId = classId,
            key = AstTreeGeneratedKey,
            classKind = ClassKind.OBJECT
        ).symbol
    }

    override fun generateConstructors(context: MemberGenerationContext): List<FirConstructorSymbol> {
        return listOf(createDefaultPrivateConstructor(context.owner, AstTreeGeneratedKey).symbol)
    }

    override fun hasPackage(packageFqName: FqName): Boolean {
        return true
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    override fun getTopLevelClassIds(): Set<ClassId> {
        return target.mapTo(mutableSetOf()) {
            when (it) {
                is FirClassSymbol -> error("//Todo сделать поддержку классов")

                is FirFunctionSymbol -> {
                    val nameCamel = it.callableId.callableName.identifier.replaceFirstChar { ch ->
                        if (ch.isLowerCase()) ch.titlecase() else ch.toString()
                    }

                    ClassId(
                        it.callableId.packageName,
                        Name.identifier(nameCamel + "AstTree")
                    )
                }

                else -> error(
                    """
                    The AST annotation does not support this declaration: $it
                """.trimIndent()
                )
            }
        }
    }

    override fun getCallableNamesForClass(
        classSymbol: FirClassSymbol<*>,
        context: MemberGenerationContext
    ): Set<Name> {
        return setOf(PropertyAstName, SpecialNames.INIT)
    }

    override fun generateProperties(
        callableId: CallableId,
        context: MemberGenerationContext?
    ): List<FirPropertySymbol> {
        val owner = context?.owner ?: return emptyList()

        val propertyAst = createMemberProperty(
            owner = owner,
            key = AstTreeGeneratedKey,
            name = PropertyAstName,
            returnType = session.builtinTypes.stringType.coneType,
        )

        return listOf(propertyAst.symbol)
    }

    override fun FirDeclarationPredicateRegistrar.registerPredicates() {
        register(FirAstTreePredicates.annotatedWithAstTree)
    }

    companion object {
        val PropertyAstName = Name.identifier("ast")
    }
}