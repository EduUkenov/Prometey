package org.prometey.ast.tree.compiler.plugin.fir

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.utils.memberDeclarationNameOrNull
import org.jetbrains.kotlin.fir.extensions.ExperimentalTopLevelDeclarationsGenerationApi
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.MemberGenerationContext
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.packageFqName
import org.jetbrains.kotlin.fir.plugin.createConeType
import org.jetbrains.kotlin.fir.plugin.createDefaultPrivateConstructor
import org.jetbrains.kotlin.fir.plugin.createMemberProperty
import org.jetbrains.kotlin.fir.plugin.createTopLevelClass
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.SymbolInternals
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.SpecialNames
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeClassIds
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeEntityNames
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeGeneratedClazzKey
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeGeneratedKey
import org.prometey.ast.tree.compiler.plugin.shared.Identifier

@OptIn(SymbolInternals::class)
class AstTreeResolveExtension(
    session: FirSession
) : FirDeclarationGenerationExtension(session) {

    val target by lazy {
        session.predicateBasedProvider.getSymbolsByPredicate(FirAstTreePredicates.annotatedWithAstTreeLookup)
    }

    val complianceTarget = mutableSetOf<Pair<Identifier, Identifier.Clazz>>()

    @ExperimentalTopLevelDeclarationsGenerationApi
    override fun generateTopLevelClassLikeDeclaration(classId: ClassId): FirClassLikeSymbol<*>? {
        val target = complianceTarget.find { (_, generated) ->
            classId == generated.classId
        }!!

        return createTopLevelClass(
            classId = classId,
            key = AstTreeGeneratedClazzKey(
                source = target.first,
                generated = target.second
            ),
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
        val result = target.mapTo(mutableSetOf()) { firSymbol ->
            val (_, resolveClassId) = firSymbol.complianceResolve()
            resolveClassId.classId
        }

        return result
    }

    private fun FirBasedSymbol<*>.complianceResolve(): Pair<Identifier, Identifier.Clazz> {
        val name = memberDeclarationNameOrNull!!.identifier
            .let { text ->
                if (text.first().isLowerCase()) text.replaceFirstChar { it.uppercase() } else text
            } + AstTreeEntityNames.AST_TREE

        val result = when (this) {
            is FirCallableSymbol<*> -> Identifier.Callable(this.callableId!!) to Identifier.Clazz(
                ClassId(this.packageFqName(), Name.identifier(name))
            )

            else -> error("compliance resolve failed")
        }

        complianceTarget += result

        return result
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