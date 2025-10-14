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
fun IrBuilderWithScope.rccFqName(
    packageName: String,
    name: String,
): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
    type = astTreeContext.rccIrFqNameImpl.defaultType,
    constructorSymbol = astTreeContext.rccIrFqNameImpl.owner.primaryConstructor?.symbol
        ?: error("Not found constructor at: ${astTreeContext.rccIrFqNameImpl}"),
).apply {
    arguments[0] = irString(packageName)
    arguments[0] = rccName(name)
}
