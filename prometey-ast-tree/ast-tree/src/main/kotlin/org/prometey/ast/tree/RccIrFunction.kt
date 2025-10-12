package org.prometey.ast.tree

import org.prometey.ast.tree.name.RccFqName

interface RccIrFunction : RccIrElement {

    val fqName: RccFqName

    val parametersType: List<RccIrType>

    val returnType: RccIrType

    val extensionType: RccIrType?

    val body: RccIrBody
}