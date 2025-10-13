package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.backend.wasm.ir2wasm.generateDefaultInitializerForType
import org.jetbrains.kotlin.codegen.generateAssertionsDisabledFieldInitialization
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.createExpressionBody
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.prometey.ast.tree.compiler.plugin.AstTreeGeneratedKey

class AstTreeForBodyGenerator(
    val pluginContext: IrPluginContext
) : IrVisitorVoid() {

    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)
    }

    override fun visitProperty(declaration: IrProperty) {
        val origin = declaration.origin
        if (origin !is IrDeclarationOrigin.GeneratedByPlugin || origin.pluginKey == AstTreeGeneratedKey) return

        val builder = DeclarationIrBuilder(pluginContext, declaration.symbol, -1, -1)

        declaration.backingField?.initializer = pluginContext.irFactory.createExpressionBody(
            expression = builder.irString("Edu")
        )
    }
}