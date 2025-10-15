package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.createExpressionBody
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.prometey.ast.tree.compiler.plugin.AstTreeGeneratedKey
import org.prometey.ast.tree.compiler.plugin.ir.mappping.rccIrFunction
import org.prometey.ast.tree.compiler.plugin.ir.mappping.rccIrTree

class AstTreeForBodyGenerator(
    val pluginContext: AstTreeContext
) : IrVisitorVoid() {
    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)
    }

    override fun visitProperty(declaration: IrProperty) {
        with(pluginContext) {
            val origin = declaration.origin
            if (origin !is IrDeclarationOrigin.GeneratedByPlugin || origin.pluginKey != AstTreeGeneratedKey) return

            val builder = DeclarationIrBuilder(pluginContext, declaration.symbol, -1, -1)

            declaration.backingField?.initializer = pluginContext.irFactory.createExpressionBody(
                expression = builder.rccIrTree(builder.rccIrFunction())
            )
        }
    }
}