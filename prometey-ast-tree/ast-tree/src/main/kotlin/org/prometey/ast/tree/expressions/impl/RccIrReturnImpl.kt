package org.prometey.ast.tree.expressions.impl

import org.prometey.ast.tree.expressions.RccIrReturn
import org.prometey.ast.tree.visitor.RccIrVisitor

@PublishedApi
internal class RccIrReturnImpl : RccIrReturn {

    override fun <R> accept(visitor: RccIrVisitor<R>): R =
        visitor.visitReturn(this)
}