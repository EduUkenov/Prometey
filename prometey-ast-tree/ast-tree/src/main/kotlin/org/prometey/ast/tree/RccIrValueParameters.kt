package org.prometey.ast.tree

class RccIrValueParameters {

}

fun MutableSet<RccIrValueParameters>.rccValueParameters(
    block: RccIrValueParameters.() -> Unit
) {
    this += RccIrValueParameters().apply(block)
}