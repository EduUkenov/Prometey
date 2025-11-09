package org.prometey.ast.tree.expressions

import org.prometey.ast.tree.RccIrElement
import org.prometey.ast.tree.declarations.RccIrFunction

interface RccIrFunctionExpression : RccIrElement {
    val function: RccIrFunction
}