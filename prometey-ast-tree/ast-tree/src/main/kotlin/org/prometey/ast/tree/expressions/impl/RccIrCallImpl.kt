package org.prometey.ast.tree.expressions.impl

import org.prometey.ast.tree.expressions.RccIrCall
import org.prometey.ast.tree.visitor.RccIrVisitor

class RccIrCallImpl : RccIrCall {

    override fun <R> accept(visitor: RccIrVisitor<R>): R =
        visitor.visitCall(this)
}