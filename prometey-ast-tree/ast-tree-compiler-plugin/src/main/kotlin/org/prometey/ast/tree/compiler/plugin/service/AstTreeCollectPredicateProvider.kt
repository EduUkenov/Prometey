package org.prometey.ast.tree.compiler.plugin.service

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.caches.firCachesFactory
import org.jetbrains.kotlin.fir.caches.getValue
import org.jetbrains.kotlin.fir.extensions.FirExtensionSessionComponent
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.prometey.ast.tree.compiler.plugin.FirAstTreePredicates

class AstTreeCollectPredicateProvider(
    session: FirSession
) : FirExtensionSessionComponent(session) {
    val astTreePredicate by session.firCachesFactory.createLazyValue {
        session.predicateBasedProvider.getSymbolsByPredicate(FirAstTreePredicates.annotatedWithAstTreeLookup)
    }
}

val FirSession.astTreeCollectPredicateProvider: AstTreeCollectPredicateProvider by FirSession.sessionComponentAccessor()

