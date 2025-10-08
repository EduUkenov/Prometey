package org.prometey.ast.tree

class RccIrLambda {
    lateinit var body: RccIrBody
}

fun rccLambda(
    block: RccIrLambda.() -> Unit
): RccIrLambda = RccIrLambda()