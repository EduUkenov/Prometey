package org.prometey.ast.tree.expressions.impl

import org.prometey.ast.tree.RccIrElement
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.expressions.RccIrCall
import org.prometey.ast.tree.visitor.RccIrVisitor

class RccIrCallImpl(
    override val element: RccIrFunction,
    override val valueArguments: List<RccIrElement?>
) : RccIrCall {
    override fun <R> accept(visitor: RccIrVisitor<R>): R = visitor.visitCall(this)
}