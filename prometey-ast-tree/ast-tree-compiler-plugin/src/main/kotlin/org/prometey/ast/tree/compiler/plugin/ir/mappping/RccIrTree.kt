package org.prometey.ast.tree.compiler.plugin.ir.mappping

import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope
import org.jetbrains.kotlin.ir.builders.irAs
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.prometey.ast.tree.compiler.plugin.ir.AstTreeContext

context(astTreeContext: AstTreeContext)
fun IrBuilderWithScope.rccIrTree(
    element: IrExpression,
): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
    type = astTreeContext.rccIrTreeImpl.defaultType,
    constructorSymbol = astTreeContext.rccIrTreeImpl.owner.primaryConstructor?.symbol
        ?: error("Not found constructor at: ${astTreeContext.rccIrTreeImpl}"),
).apply {
    arguments[0] = irAs(element, astTreeContext.rccIrElement.defaultType)
}