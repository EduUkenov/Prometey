package org.prometey.ast.tree.declarations

import org.prometey.ast.tree.RccIrElement

interface RccIrValueParameter : RccIrElement {

    val defaultValue: RccIrElement
}