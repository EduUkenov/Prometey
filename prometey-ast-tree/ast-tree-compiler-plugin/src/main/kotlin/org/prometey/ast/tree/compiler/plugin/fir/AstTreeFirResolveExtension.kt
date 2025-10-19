package org.prometey.ast.tree.compiler.plugin.fir

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression
import org.jetbrains.kotlin.fir.extensions.ExperimentalTopLevelDeclarationsGenerationApi
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.MemberGenerationContext
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.plugin.createConeType
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
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeAnnotations
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeClassIds
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeGeneratedKey

@OptIn(SymbolInternals::class)
class AstTreeResolveExtension(
    session: FirSession
) : FirDeclarationGenerationExtension(session) {

    val target by lazy {
        session.predicateBasedProvider.getSymbolsByPredicate(FirAstTreePredicates.annotatedWithAstTreeLookup)
    }

    val target2 by lazy {
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

    override fun generateProperties(
        callableId: CallableId,
        context: MemberGenerationContext?
    ): List<FirPropertySymbol> {
        val owner = context?.owner ?: return emptyList()

        val propertyAst = createMemberProperty(
            owner = owner,
            key = AstTreeGeneratedKey,
            name = PropertyAstName,
            returnType = AstTreeClassIds.rccIrTree.createConeType(session),
        )

        return listOf(propertyAst.symbol)
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    override fun getTopLevelClassIds(): Set<ClassId> {
        return target.mapTo(mutableSetOf()) { firSymbol ->
            when (firSymbol) {
                is FirClassSymbol -> error("//Todo сделать поддержку классов")

                is FirFunctionSymbol -> {
                    val name = firSymbol.annotations
                        .filterIsInstance<FirAnnotationCall>()
                        .find {
                            session.predicateBasedProvider.matches(FirAstTreePredicates.annotatedWithAstTree, firSymbol)
                        }?.argumentList?.arguments?.first() as FirLiteralExpression

                    ClassId(
                        firSymbol.callableId.packageName,
                        Name.identifier(name.value.toString() + "AstTree")
                    )
                }

                else -> error(
                    """
                    The AST annotation does not support this declaration: $firSymbol
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

    override fun FirDeclarationPredicateRegistrar.registerPredicates() {
        register(FirAstTreePredicates.annotatedWithAstTree)
    }

    companion object {
        val PropertyAstName = Name.identifier("ast")
    }
}