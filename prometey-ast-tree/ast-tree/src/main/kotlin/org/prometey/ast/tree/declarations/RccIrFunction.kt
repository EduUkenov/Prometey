package org.prometey.ast.tree.declarations

import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.RccIrElement

interface RccIrFunction : RccIrElement {

    val fqName: RccFqName

    val typeParameters: List<RccIrType>

    val returnType: RccIrType

    val extensionType: RccIrType?

    val body: RccIrBody?
}