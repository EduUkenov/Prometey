package org.prometey.ast.tree.compiler.plugin.ir.mappping

import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.prometey.ast.tree.compiler.plugin.ir.AstTreeContext

context(astTreeContext: AstTreeContext)
fun IrBuilderWithScope.rccIrFunction(
    //irSimpleFunction: IrSimpleFunction
): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
    type = astTreeContext.rccIrFunctionImpl.defaultType,
    constructorSymbol = astTreeContext.rccIrFunctionImpl.owner.primaryConstructor?.symbol
        ?: error("Not found constructor at: ${astTreeContext.rccIrFunctionImpl}"),
).apply {
    arguments[0] = rccFqName(
        packageName = "irSimpleFunction.fqNameWhenAvailable!!.toString()",
        //name = "irSimpleFunction.name.toString()"
    )
}