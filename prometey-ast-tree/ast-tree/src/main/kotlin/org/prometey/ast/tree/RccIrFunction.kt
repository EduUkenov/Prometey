package org.prometey.ast.tree

class RccIrFunction(
    val name: String = "",
) : RccIrElement {
    lateinit var returnType: RccIrType
    lateinit var parameters: List<RccIrValueParameters>
}

fun rccIrFunction(
    name: String,
    block: RccIrFunction.() -> Unit = {}
): RccIrFunction = RccIrFunction(
    name = name
).apply(block)