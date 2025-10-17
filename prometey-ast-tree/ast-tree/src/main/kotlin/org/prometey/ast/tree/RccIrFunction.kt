package org.prometey.ast.tree

interface RccIrFunction : RccIrElement {

    val fqName: RccFqName

    val parametersType: List<RccIrType>

    val returnType: RccIrType

    val extensionType: RccIrType?

    val body: RccIrBody
}