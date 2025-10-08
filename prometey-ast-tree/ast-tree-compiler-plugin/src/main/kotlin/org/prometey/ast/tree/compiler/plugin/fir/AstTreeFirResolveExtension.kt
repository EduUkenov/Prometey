package org.prometey.ast.tree.compiler.plugin.fir

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.extensions.ExperimentalTopLevelDeclarationsGenerationApi
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.plugin.createTopLevelClass
import org.jetbrains.kotlin.fir.symbols.SymbolInternals
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.prometey.ast.tree.compiler.plugin.FirAstTreePredicates
import org.prometey.ast.tree.compiler.plugin.FirAstTreePredicates.annotatedWithAstTreeLookup

@OptIn(SymbolInternals::class)
class AstTreeResolveExtension(
    session: FirSession
) : FirDeclarationGenerationExtension(session) {
    val matchedAstTree by lazy {
        session.predicateBasedProvider.getSymbolsByPredicate(annotatedWithAstTreeLookup)
    }

    fun edu() {
        matchedAstTree.first().fir.accept(AstTreeVisitor(session))
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    override fun generateTopLevelClassLikeDeclaration(classId: ClassId): FirClassLikeSymbol<*>? {
        edu()

        return createTopLevelClass(
            classId = classId,
            key = AstTreeGeneratedKey,
            classKind = ClassKind.OBJECT
        ).symbol
    }

    override fun hasPackage(packageFqName: FqName): Boolean {
        return true
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    override fun getTopLevelClassIds(): Set<ClassId> = matchedAstTree.mapTo(mutableSetOf()) {
        when (it) {
            is FirClassSymbol -> error("//Todo сделать поддержку классов")

            is FirFunctionSymbol -> {
                val nameCamel = it.callableId.callableName.identifier.replaceFirstChar { ch ->
                    if (ch.isLowerCase()) ch.titlecase() else ch.toString()
                }

                ClassId(
                    packageFqName = it.callableId.packageName,
                    topLevelName = Name.identifier(nameCamel + "AstTree")
                )
            }

            else -> error(
                """
                    The AST annotation does not support this declaration: $it
                """.trimIndent()
            )
        }
    }

    override fun FirDeclarationPredicateRegistrar.registerPredicates() {
        register(FirAstTreePredicates.annotatedWithAstTree)
    }
}

//companion object {
//    val MY_CLASS_ID =
//        ClassId(FqName.fromSegments(listOf("foo", "bar")), Name.identifier("BoxAstTree"))
//    val FOO_ID = CallableId(MY_CLASS_ID, Name.identifier("foo"))
//}

//override fun getCallableNamesForClass(
//    classSymbol: FirClassSymbol<*>,
//    context: MemberGenerationContext
//): Set<Name> {
//    return setOf(FOO_ID.callableName, SpecialNames.INIT)
//}

//    override fun generateFunctions(
//        callableId: CallableId,
//        context: MemberGenerationContext?
//    ): List<FirNamedFunctionSymbol> {
//        val owner = context?.owner ?: return emptyList()
//        val function = createMemberFunction(
//            owner,
//            AstTreeGeneratedKey,
//            callableId.callableName,
//            returnType = session.builtinTypes.stringType.coneType
//        )
//        return listOf(function.symbol)
//    }