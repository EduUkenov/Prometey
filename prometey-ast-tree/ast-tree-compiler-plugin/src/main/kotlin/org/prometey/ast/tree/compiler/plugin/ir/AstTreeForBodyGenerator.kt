package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope
import org.jetbrains.kotlin.ir.builders.irAs
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.createExpressionBody
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.prometey.ast.tree.compiler.plugin.AstTreeClassIds
import org.prometey.ast.tree.compiler.plugin.AstTreeGeneratedKey

class AstTreeForBodyGenerator(
    val pluginContext: IrPluginContext
) : IrVisitorVoid() {

    val rccIrTreeImpl = pluginContext.referenceClass(AstTreeClassIds.rccIrTreeImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrTreeImpl}")

    val rccIrElement = pluginContext.referenceClass(AstTreeClassIds.rccIrElement)
        ?: error("Not found: ${AstTreeClassIds.rccIrElement}")

    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)
    }

    override fun visitProperty(declaration: IrProperty) {
        with(pluginContext) {
            val origin = declaration.origin
            if (origin !is IrDeclarationOrigin.GeneratedByPlugin || origin.pluginKey != AstTreeGeneratedKey) return

            val builder = DeclarationIrBuilder(pluginContext, declaration.symbol, -1, -1)

            declaration.backingField?.initializer = pluginContext.irFactory.createExpressionBody(
                expression = builder.rccIrTree(TODO())
            )
        }
    }

    fun IrBuilderWithScope.rccIrTree(
        expressionAst: IrExpression,
    ): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
        type = rccIrTreeImpl.defaultType,
        constructorSymbol = rccIrTreeImpl.owner.primaryConstructor?.symbol
            ?: error("Not found constructor at: $rccIrTreeImpl}"),
    ).apply {
        arguments[0] = irAs(expressionAst, rccIrElement.defaultType)
    }


}