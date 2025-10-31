package org.prometey.ast.tree.declarations.impl

import org.prometey.ast.tree.declarations.RccIrBody
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.declarations.RccIrType
import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.visitor.RccIrVisitor

@PublishedApi
internal class RccIrFunctionImpl(
    override val fqName: RccFqName,
    override val body: RccIrBody,
) : RccIrFunction {
    override val parametersType: List<RccIrType>
        get() = TODO("Not yet implemented")
    override val returnType: RccIrType
        get() = TODO("Not yet implemented")
    override val extensionType: RccIrType?
        get() = TODO("Not yet implemented")

    override fun <R> accept(visitor: RccIrVisitor<R>): R = visitor.visitIrFunction(this)
}