package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.jetbrains.kotlin.ir.visitors.IrVisitor
import org.prometey.ast.tree.compiler.plugin.AstTreeClassIds

class AstTreeTranspilation(
    pluginContext: IrPluginContext
) {
    val rccIrElement = pluginContext.referenceClass(AstTreeClassIds.rccIrElement)
        ?: error("Not found: ${AstTreeClassIds.rccIrElement}")

    val rccNameImpl = pluginContext.referenceClass(AstTreeClassIds.rccNameImpl)
        ?: error("Not found: ${AstTreeClassIds.rccNameImpl}")

    val rccFqNameImpl = pluginContext.referenceClass(AstTreeClassIds.rccFqNameImpl)
        ?: error("Not found: ${AstTreeClassIds.rccFqNameImpl}")

    val rccIrBodyImpl = pluginContext.referenceClass(AstTreeClassIds.rccIrBodyImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrBodyImpl}")

    val rccIrFunctionImpl = pluginContext.referenceClass(AstTreeClassIds.rccIrFunctionImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrFunctionImpl}")

//    val rccIrLambdaImpl = referenceClass(AstTreeClassIds.rccIrLambdaImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccIrLambdaImpl}")

//    val rccIrTypeImpl = referenceClass(AstTreeClassIds.rccIrTypeImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccIrTypeImpl}")

    inner class AstTreeVisitor(
        val pluginContext: IrPluginContext
    ) : IrVisitor<IrExpression, IrExpression>() {
        override fun visitElement(
            element: IrElement,
            data: IrExpression
        ): IrExpression {
            TODO("Not yet implemented")
        }

        override fun visitSimpleFunction(
            declaration: IrSimpleFunction,
            data: IrExpression
        ): IrExpression {
            val builder = DeclarationIrBuilder(pluginContext, declaration.symbol)
            return builder.rccIrFunction(declaration)
        }

        fun IrBuilderWithScope.rccName(
            name: String,
        ): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
            type = rccNameImpl.defaultType,
            constructorSymbol = rccNameImpl.owner.primaryConstructor?.symbol
                ?: error("Not found constructor at: $rccNameImpl"),
        ).apply {
            arguments[0] = irString(name)
        }

        private fun IrBuilderWithScope.rccIrFunction(
            irSimpleFunction: IrSimpleFunction,
        ): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
            type = rccIrFunctionImpl.defaultType,
            constructorSymbol = rccIrFunctionImpl.owner.primaryConstructor?.symbol ?: error("Not found constructor at: $rccIrFunctionImpl"),
        ).apply {
            arguments[0] = rccFqName(
                packageName = irSimpleFunction.fqNameWhenAvailable?.asString() ?: error("Not package name by: ${irSimpleFunction.dump()}"),
                name = irSimpleFunction.fqNameWhenAvailable?.shortName()?.asString() ?: error("Not name by: ${irSimpleFunction.dump()}"),
            )
        }

        fun IrBuilderWithScope.rccFqName(
            packageName: String,
            name: String,
        ): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
            type = rccFqNameImpl.defaultType,
            constructorSymbol = rccFqNameImpl.owner.primaryConstructor?.symbol
                ?: error("Not found constructor at: $rccFqNameImpl"),
        ).apply {
            arguments[0] = irString(packageName)
            arguments[0] = rccName(name)
        }
    }
}