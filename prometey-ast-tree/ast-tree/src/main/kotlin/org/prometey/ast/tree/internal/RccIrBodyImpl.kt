package org.prometey.ast.tree.internal

import org.prometey.ast.tree.RccIrBody
import org.prometey.ast.tree.visitors.RccIrVisitor

class RccIrBodyImpl : RccIrBody {

    override fun <R> accept(visitor: RccIrVisitor<R>): R {
        return visitor.visitIrBody(this)
    }
}