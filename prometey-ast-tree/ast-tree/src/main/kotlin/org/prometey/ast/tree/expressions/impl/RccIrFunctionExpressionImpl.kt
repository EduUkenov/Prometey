package org.prometey.ast.tree.expressions.impl

import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.expressions.RccIrFunctionExpression
import org.prometey.ast.tree.visitor.RccIrVisitor

@PublishedApi
internal class RccIrFunctionExpressionImpl(
    override val function: RccIrFunction
) : RccIrFunctionExpression {

    override fun <R> accept(visitor: RccIrVisitor<R>): R {
        return visitor.visitIrFunctionExpression(this)
    }
}