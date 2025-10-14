package org.prometey.ast.tree.visitors

import org.prometey.ast.tree.RccIrBody
import org.prometey.ast.tree.RccIrFunction
import org.prometey.ast.tree.RccIrType

interface RccIrVisitor<R> {

    fun visitIrFunction(function: RccIrFunction): R

    fun visitIrBody(block: RccIrBody): R

    fun visitIrType(type: RccIrType): R
}