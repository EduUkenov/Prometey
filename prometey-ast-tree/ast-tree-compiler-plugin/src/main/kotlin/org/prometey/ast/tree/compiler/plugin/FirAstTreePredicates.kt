package org.prometey.ast.tree.compiler.plugin

import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate
import org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate

object FirAstTreePredicates {
    internal val annotatedWithAstTree = DeclarationPredicate.create {
        annotated(setOf(AstTreeAnnotations.astTreeAnnotationFqName))
    }

    internal val annotatedWithAstTreeLookup = LookupPredicate.create {
        annotated(setOf(AstTreeAnnotations.astTreeAnnotationFqName))
    }
}