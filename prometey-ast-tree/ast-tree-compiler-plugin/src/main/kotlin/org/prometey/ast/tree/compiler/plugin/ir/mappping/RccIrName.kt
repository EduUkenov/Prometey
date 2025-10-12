package org.prometey.ast.tree.compiler.plugin.ir.mappping

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.prometey.ast.tree.compiler.plugin.EntityNames

context(pluginContext: IrPluginContext)
fun IrBuilderWithScope.rccName(
    name: String,
): IrConstructorCall {
    val clazz = pluginContext.referenceClass(EntityNames.rccNameClassId)
        ?: error("Not found: ${EntityNames.rccNameClassId}")

    return IrConstructorCallImpl.fromSymbolOwner(
        type = clazz.defaultType,
        constructorSymbol = clazz.owner.primaryConstructor!!.symbol,
    ).apply {
        arguments[0] = irString(name)
    }
}