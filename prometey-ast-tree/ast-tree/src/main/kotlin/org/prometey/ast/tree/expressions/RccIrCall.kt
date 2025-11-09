package org.prometey.ast.tree.expressions

import org.prometey.ast.tree.RccIrElement

interface RccIrCall : RccIrElement {
    val element: RccIrElement

    val valueArguments: List<RccIrElement?>
}