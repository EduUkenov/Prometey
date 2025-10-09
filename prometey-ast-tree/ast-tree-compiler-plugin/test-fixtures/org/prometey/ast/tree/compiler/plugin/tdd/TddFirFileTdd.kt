package org.prometey.ast.tree.compiler.plugin.tdd

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.extensions.ExperimentalTopLevelDeclarationsGenerationApi
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.symbols.SymbolInternals
import org.jetbrains.kotlin.name.ClassId
import org.prometey.ast.tree.compiler.plugin.service.astTreeCollectPredicateProvider

internal class TddFirFileTdd(
    session: FirSession
) : FirDeclarationGenerationExtension(session) {

    @OptIn(SymbolInternals::class)
    @ExperimentalTopLevelDeclarationsGenerationApi
    override fun getTopLevelClassIds(): Set<ClassId> {
        session.astTreeCollectPredicateProvider.astTreePredicate.forEach {
            it.fir.accept(TddFirVisitor())
        }

        return setOf()
    }
}