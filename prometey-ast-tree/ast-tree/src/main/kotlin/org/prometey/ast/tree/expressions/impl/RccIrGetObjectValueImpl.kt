package org.prometey.ast.tree.expressions.impl

import org.prometey.ast.tree.expressions.RccIrGetObjectValue
import org.prometey.ast.tree.visitor.RccIrVisitor

@PublishedApi
internal class RccIrGetObjectValueImpl() : RccIrGetObjectValue {

    override fun <R> accept(visitor: RccIrVisitor<R>): R {
        return visitor.visitIrGetObjectValue(this)
    }

}