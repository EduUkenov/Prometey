package org.prometey.ast.tree.compiler.plugin.fir

import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate
import org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate
import org.prometey.ast.tree.compiler.plugin.arhive.AstTreeAnnotations

object FirAstTreePredicates {
    internal val annotatedWithAstTree = DeclarationPredicate.Companion.create {
        annotated(setOf(AstTreeAnnotations.astTreeAnnotationFqName))
    }

    internal val annotatedWithAstTreeLookup = LookupPredicate.Companion.create {
        annotated(setOf(AstTreeAnnotations.astTreeAnnotationFqName))
    }
}