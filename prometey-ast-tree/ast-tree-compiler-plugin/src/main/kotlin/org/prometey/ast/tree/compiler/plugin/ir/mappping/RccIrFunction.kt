package org.prometey.ast.tree.compiler.plugin.ir.mappping

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.prometey.ast.tree.compiler.plugin.EntityNames

context(pluginContext: IrPluginContext)
fun IrBuilderWithScope.rccIrFunction(
    irSimpleFunction: IrSimpleFunction
): IrConstructorCall {
    val clazz = pluginContext.referenceClass(EntityNames.rccIrFunctionImplClassId)
        ?: error("Not found: ${EntityNames.rccIrFunctionImplClassId}")

    return IrConstructorCallImpl.fromSymbolOwner(
        type = clazz.defaultType,
        constructorSymbol = clazz.owner.primaryConstructor!!.symbol,
    ).apply {
        arguments[0] = rccFqName(
            packageName = irSimpleFunction.fqNameWhenAvailable!!.toString(),
            name = irSimpleFunction.name.toString()
        )
    }
}