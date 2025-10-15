package org.prometey.ast.tree.compiler.plugin.ir.mappping

import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.prometey.ast.tree.compiler.plugin.ir.AstTreeContext

context(astTreeContext: AstTreeContext)
fun IrBuilderWithScope.rccName(
    name: String,
): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
    type = astTreeContext.rccNameImpl.defaultType,
    constructorSymbol = astTreeContext.rccNameImpl.owner.primaryConstructor?.symbol
        ?: error("Not found constructor at: ${astTreeContext.rccNameImpl}"),
).apply {
    arguments[0] = irString(name)
}