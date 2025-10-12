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
fun IrBuilderWithScope.rccFqName(
    packageName: String,
    name: String,
): IrConstructorCall {
    val clazz = pluginContext.referenceClass(EntityNames.rccFqNameClassId)
        ?: error("Not found: ${EntityNames.rccFqNameClassId}")

    return IrConstructorCallImpl.fromSymbolOwner(
        type = clazz.defaultType,
        constructorSymbol = clazz.owner.primaryConstructor!!.symbol,
    ).apply {
        arguments[0] = irString(packageName)
        arguments[0] = rccName(name)
    }
}