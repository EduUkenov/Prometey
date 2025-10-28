package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.visitors.IrVisitor
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeClassIds

class VisitorLower : IrVisitorVoid() {
    override fun visitElement(element: IrElement) {
        println("RccLog visitElement")
        element.acceptChildren(this, null)
    }

    override fun visitPackageFragment(declaration: IrPackageFragment) {
        println("RccLog visitPackageFragment")
        super.visitPackageFragment(declaration)
    }

    override fun visitSimpleFunction(declaration: IrSimpleFunction) {
        println("RccLog visitSimpleFunction")
    }
}

class AstTreeTranspilation(
    pluginContext: IrPluginContext
) {
    val rccIrFunctionImpl = pluginContext.referenceClass(AstTreeClassIds.rccIrFunctionImpl)!!

    private inner class Visitor : IrVisitor<IrExpression, Nothing?>() {
        lateinit var expression: IrExpression

        override fun visitElement(
            element: IrElement,
            data: Nothing?
        ): IrExpression {
            expression = element.accept(this, null)
            return expression
        }

        override fun visitModuleFragment(
            declaration: IrModuleFragment,
            data: Nothing?
        ): IrExpression = error("It should not support this call.")

//        override fun visitSimpleFunction(
//            declaration: IrSimpleFunction,
//            data: Nothing?
//        ): IrExpression {
//
//        }

    }
}