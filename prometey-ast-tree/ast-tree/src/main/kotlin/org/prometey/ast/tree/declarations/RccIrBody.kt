package org.prometey.ast.tree.declarations

import org.prometey.ast.tree.RccIrElement

interface RccIrBody : RccIrElement {
    val elements: List<RccIrElement>
}