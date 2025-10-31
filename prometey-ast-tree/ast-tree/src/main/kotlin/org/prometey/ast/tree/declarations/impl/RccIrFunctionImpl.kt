package org.prometey.ast.tree.declarations.impl

import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.declarations.RccIrBody
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.declarations.RccIrType

@PublishedApi
internal class RccIrFunctionImpl(
    override val fqName: RccFqName,
) : RccIrFunction {
    override val parametersType: List<RccIrType>
        get() = TODO("Not yet implemented")
    override val returnType: RccIrType
        get() = TODO("Not yet implemented")
    override val extensionType: RccIrType?
        get() = TODO("Not yet implemented")
    override val body: RccIrBody get() = TODO("Not yet implemented")
}