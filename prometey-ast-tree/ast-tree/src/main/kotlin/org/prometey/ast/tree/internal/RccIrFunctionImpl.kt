package org.prometey.ast.tree.internal

import org.prometey.ast.tree.RccIrBody
import org.prometey.ast.tree.RccIrFunction
import org.prometey.ast.tree.RccIrType
import org.prometey.ast.tree.RccFqName
import org.prometey.ast.tree.visitors.RccIrVisitor

internal class RccIrFunctionImpl(
    override val fqName: RccFqName
//    override val returnType: RccIrType,
//    override val parametersType: List<RccIrType>,
//    override val extensionType: RccIrType?,
//    override val body: RccIrBody,
) : RccIrFunction {

    override fun <R> accept(visitor: RccIrVisitor<R>): R {
        return visitor.visitIrFunction(this)
    }

    override val parametersType: List<RccIrType>
        get() = TODO("Not yet implemented")
    override val returnType: RccIrType
        get() = TODO("Not yet implemented")
    override val extensionType: RccIrType?
        get() = TODO("Not yet implemented")
    override val body: RccIrBody
        get() = TODO("Not yet implemented")
}