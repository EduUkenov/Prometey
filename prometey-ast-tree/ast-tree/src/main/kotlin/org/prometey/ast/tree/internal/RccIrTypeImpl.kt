package org.prometey.ast.tree.internal

import org.prometey.ast.tree.RccIrType
import org.prometey.ast.tree.visitors.RccIrVisitor

class RccIrTypeImpl : RccIrType {

    override fun <R> accept(visitor: RccIrVisitor<R>): R {
        return visitor.visitIrType(this)
    }
}