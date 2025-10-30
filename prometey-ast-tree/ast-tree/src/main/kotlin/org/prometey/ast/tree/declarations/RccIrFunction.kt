package org.prometey.ast.tree.declarations

import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.declarations.RccIrBody
import org.prometey.ast.tree.RccIrElement
import org.prometey.ast.tree.declarations.RccIrType

interface RccIrFunction : RccIrElement {

    val fqName: RccFqName

    val parametersType: List<RccIrType>

    val returnType: RccIrType

    val extensionType: RccIrType?

    val body: RccIrBody
}