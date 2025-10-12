package org.prometey.ast.tree.internal

import org.prometey.ast.tree.RccIrBody
import org.prometey.ast.tree.RccIrFunction
import org.prometey.ast.tree.RccIrType
import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.visitors.RccIrVisitor

class RccIrFunctionImpl(
    override val fqName: RccFqName,
    override val returnType: RccIrType,
    override val parametersType: List<RccIrType>,
    override val extensionType: RccIrType?,
    override val body: RccIrBody,
) : RccIrFunction {

    override fun <R> accept(visitor: RccIrVisitor<R>): R {
        return visitor.visitIrFunction(this)
    }
}