package org.prometey.ast.tree.visitor

import org.prometey.ast.tree.declarations.RccIrFunction

interface RccIrVisitor<R> {
    fun visitIrFunction(function: RccIrFunction): R
}