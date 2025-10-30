package org.prometey.ast.tree.declarations.impl

import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.declarations.RccIrBody
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.declarations.RccIrType

@PublishedApi
internal class RccIrFunctionImpl(
    override val fqName: RccFqName,
    override val returnType: RccIrType,
    override val parametersType: List<RccIrType>,
    override val extensionType: RccIrType?,
) : RccIrFunction {
    override val body: RccIrBody get() = TODO("Not yet implemented")
}