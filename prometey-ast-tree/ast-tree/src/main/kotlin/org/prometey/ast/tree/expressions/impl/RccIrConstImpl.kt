package org.prometey.ast.tree.expressions.impl

import org.prometey.ast.tree.expressions.RccIrConst
import org.prometey.ast.tree.visitor.RccIrVisitor

@PublishedApi
internal class RccIrConstImpl : RccIrConst {

    override fun <R> accept(visitor: RccIrVisitor<R>): R =
        visitor.visitIrConst(this)
}