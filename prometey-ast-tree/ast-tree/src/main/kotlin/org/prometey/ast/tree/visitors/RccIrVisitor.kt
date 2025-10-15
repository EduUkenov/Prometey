package org.prometey.ast.tree.visitors

import org.prometey.ast.tree.RccIrFunction

interface RccIrVisitor<R> {

    fun visitIrFunction(function: RccIrFunction): R
}