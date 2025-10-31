package org.prometey.ast.tree.declarations.impl

import org.prometey.ast.tree.RccIrElement
import org.prometey.ast.tree.declarations.RccIrBody
import org.prometey.ast.tree.visitor.RccIrVisitor

@PublishedApi
internal class RccIrBodyImpl(
    override val elements: List<RccIrElement>
) : RccIrBody {
    override fun <R> accept(visitor: RccIrVisitor<R>): R =
        visitor.visitIrBody(this)
}