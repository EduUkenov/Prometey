package org.prometey.ast.tree.visitor

import org.prometey.ast.tree.declarations.RccIrBody
import org.prometey.ast.tree.expressions.RccIrCall
import org.prometey.ast.tree.declarations.RccIrFunction

interface RccIrVisitor<R> {
    fun visitIrFunction(function: RccIrFunction): R

    fun visitIrBody(body: RccIrBody): R

    fun visitCall(expression: RccIrCall): R
}