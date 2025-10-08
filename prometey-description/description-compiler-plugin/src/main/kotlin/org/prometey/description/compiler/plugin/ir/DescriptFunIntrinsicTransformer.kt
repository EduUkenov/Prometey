package org.prometey.description.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.createIrBuilder
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope
import org.jetbrains.kotlin.ir.builders.irCallConstructor
import org.jetbrains.kotlin.ir.builders.irGetObject
import org.jetbrains.kotlin.ir.builders.irInt
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrParameterKind
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.IrPropertyReference
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.classOrNull
import org.jetbrains.kotlin.ir.types.isByte
import org.jetbrains.kotlin.ir.types.isChar
import org.jetbrains.kotlin.ir.types.isDouble
import org.jetbrains.kotlin.ir.types.isFloat
import org.jetbrains.kotlin.ir.types.isInt
import org.jetbrains.kotlin.ir.types.isLong
import org.jetbrains.kotlin.ir.types.isPrimitiveType
import org.jetbrains.kotlin.ir.types.isShort
import org.jetbrains.kotlin.ir.types.isString
import org.jetbrains.kotlin.ir.types.isUnit
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.isFunction
import org.jetbrains.kotlin.ir.util.isInterface
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.jetbrains.kotlin.ir.util.render
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.resolve.calls.tower.isSynthesized
import org.prometey.description.compiler.plugin.EntityNames
import org.prometey.description.compiler.plugin.PrimitiveBuiltins

class DescriptFunIntrinsicTransformer(
    private val pluginContext: IrPluginContext,
) : IrElementTransformerVoid() {
    val descriptorFun = pluginContext
        .referenceFunctions(EntityNames.descriptorFunIntrinsic).first()

    val pluginGeneratedDescriptorFunction = pluginContext
        .referenceClass(EntityNames.pluginGeneratedDescriptorFunction)!!

    val pluginGeneratedDescriptorClass = pluginContext
        .referenceClass(EntityNames.pluginGeneratedDescriptorClass)!!

    override fun visitCall(expression: IrCall): IrExpression {
        val edu1 = expression.arguments.first()
        if (edu1 is IrFunctionExpression) {
            println("EduLog: ${edu1.function.returnType.isString()}")
        }

        if (expression.symbol == descriptorFun) {
            val descript = expression.arguments.first()

            val builder = pluginContext.irBuiltIns.createIrBuilder(
                symbol = expression.symbol,
                expression.startOffset,
                expression.endOffset
            )

            if (descript is IrFunctionReference) {
                val constructor = IrConstructorCallImpl.fromSymbolOwner(
                    type = pluginGeneratedDescriptorFunction.owner.defaultType,
                    constructorSymbol = pluginGeneratedDescriptorFunction.owner.primaryConstructor!!.symbol,
                ).apply {
                    arguments[0] = builder.irString(descript.symbol.owner.fqNameWhenAvailable.toString())
                    arguments[1] = builder.irInt(descript.symbol.owner.countParameter())
//                    arguments[2] = builder.buildDescriptorForType(descript.symbol.owner.returnType)
//                    arguments[3] = builder.buildDescriptorForType(descript.symbol.owner.extensionParameter())
                }

                return constructor
            }
        }

        return super.visitCall(expression)
    }

    fun IrBuilderWithScope.buildDescriptorForType(
        irType: IrType?
    ): IrExpression {
        return if (irType != null) {
            when {
                irType.isUnit() -> irGetObject(pluginContext.referenceClass(PrimitiveBuiltins.descriptorUnit)!!)
                irType.isPrimitiveType(false) -> irGetObject(pluginContext.referenceClass(irType.findDescriptorByPrimitive())!!)
                irType.classOrNull?.owner?.kind == ClassKind.CLASS -> buildDescriptorClass(irType)
                else -> error("Description: not found descriptor for $irType")
            }
        } else irGetObject(pluginContext.referenceClass(EntityNames.emptyDescriptor)!!)
    }

    fun IrBuilderWithScope.buildDescriptorClass(
        irType: IrType
    ): IrExpression {
        return irCallConstructor(
            callee = pluginGeneratedDescriptorClass.owner.primaryConstructor!!.symbol,
            typeArguments = emptyList()
        ).apply {
            arguments[0] = irString(irType.classOrNull!!.owner.fqNameWhenAvailable.toString())
        }
    }
}

@OptIn(ObsoleteDescriptorBasedAPI::class)
private fun IrFunction.countParameter(): Int = parameters.count {
    !it.name.toString().startsWith("$")
}

private fun IrFunction.extensionParameter(): IrType? = parameters.find {
    it.kind == IrParameterKind.ExtensionReceiver
}?.type

fun IrType.findDescriptorByPrimitive(): ClassId = when {
    isByte() -> PrimitiveBuiltins.descriptorByte
    isShort() -> PrimitiveBuiltins.descriptorShort
    isInt() -> PrimitiveBuiltins.descriptorInt
    isLong() -> PrimitiveBuiltins.descriptorLong
    isFloat() -> PrimitiveBuiltins.descriptorFloat
    isDouble() -> PrimitiveBuiltins.descriptorDouble
    isChar() -> TODO("descriptorChar")
    isString() -> PrimitiveBuiltins.descriptorString
    else -> error(TODO())
}

private fun IrType.findDescriptorUnit(): ClassId? = when {
    isUnit() -> PrimitiveBuiltins.descriptorUnit
    else -> null
}

private fun IrType.findDescriptorByExternalType(): ClassId? = when {
    classOrNull?.owner?.kind == ClassKind.CLASS -> EntityNames.pluginGeneratedDescriptorClass
    isFunction() && isInterface() -> EntityNames.pluginGeneratedDescriptorLambda
    else -> null
}