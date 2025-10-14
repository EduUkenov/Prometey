package org.prometey.ast.tree

import org.prometey.ast.tree.visitors.RccIrVisitor

interface RccIrElement {

    fun <R> accept(visitor: RccIrVisitor<R>): R
}