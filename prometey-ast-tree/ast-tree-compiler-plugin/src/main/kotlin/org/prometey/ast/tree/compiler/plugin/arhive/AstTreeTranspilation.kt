package org.prometey.ast.tree.compiler.plugin.arhive

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.visitors.IrVisitor

class AstTreeTranspilation(
    val pluginContext: IrPluginContext
) : IrVisitor<IrExpression, IrElement>() {

    //    val rccIrElement = pluginContext.referenceClass(AstTreeClassIds.rccIrElement)
//        ?: error("Not found: ${AstTreeClassIds.rccIrElement}")
//
//    val rccNameImpl = pluginContext.referenceClass(AstTreeClassIds.rccNameImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccNameImpl}")
//
//    val rccFqNameImpl = pluginContext.referenceClass(AstTreeClassIds.rccFqNameImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccFqNameImpl}")
//
//    val rccIrBodyImpl = pluginContext.referenceClass(AstTreeClassIds.rccIrBodyImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccIrBodyImpl}")
//
//    val rccIrFunctionImpl = pluginContext.referenceClass(AstTreeClassIds.rccIrFunctionImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccIrFunctionImpl}")
//
//    val rccIrTypeImpl = pluginContext.referenceClass(AstTreeClassIds.rccIrTypeImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccIrFunctionImpl}")
////    val rccIrLambdaImpl = referenceClass(AstTreeClassIds.rccIrLambdaImpl)
////        ?: error("Not found: ${AstTreeClassIds.rccIrLambdaImpl}")
//
////    val rccIrTypeImpl = referenceClass(AstTreeClassIds.rccIrTypeImpl)
////        ?: error("Not found: ${AstTreeClassIds.rccIrTypeImpl}")
//
//    val listOf = pluginContext.referenceFunctions(KotlinCollectionCallableId.listOf)
//        .first {
//            it.owner.typeParameters.size == 1 && it.owner.typeParameters.size == 1 && it.owner.parameters[0].isVararg
//        }
//
//    override fun visitElement(
//        element: IrElement,
//        data: IrElement
//    ): IrExpression {
//        return element.accept(this@AstTreeTranspilation, data)
//    }
//
//    override fun visitElement(element: IrElement, data: Unit): IrExpression {
//        return element.accept(this@AstTreeTranspilation, data)
//    }
//
//
//    override fun visitSimpleFunction(
//        declaration: IrSimpleFunction,
//        data: Unit
//    ): IrExpression {
//
//    }
//
//    override fun visitReturn(expression: IrReturn, data: Unit): IrExpression {
//        return IrConstructorCallImpl.fromSymbolOwner(
//            type = rccIrTypeImpl.defaultType,
//            constructorSymbol = rccIrTypeImpl.owner.primaryConstructor?.symbol
//                ?: error("Not found constructor at: $rccIrTypeImpl"),
//        )
//    }
//
//    override fun visitBlock(expression: IrBlock, data: Unit): IrExpression {
//        return super.visitBlock(expression, data)
//    }
//
//    fun IrBuilderWithScope.rccName(
//        name: String,
//    ): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
//        type = rccNameImpl.defaultType,
//        constructorSymbol = rccNameImpl.owner.primaryConstructor?.symbol
//            ?: error("Not found constructor at: $rccNameImpl"),
//    ).apply {
//        arguments[0] = irString(name)
//    }
//
//    private fun IrBuilderWithScope.rccIrFunction(
//        irSimpleFunction: IrSimpleFunction,
//    ): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
//        type = rccIrFunctionImpl.defaultType,
//        constructorSymbol = rccIrFunctionImpl.owner.primaryConstructor?.symbol
//            ?: error("Not found constructor at: $rccIrFunctionImpl"),
//    ).apply {
//        arguments[0] = rccFqName(
//            packageName = irSimpleFunction.fqNameWhenAvailable?.asString()
//                ?: error("Not package name by: ${irSimpleFunction.dump()}"),
//
//            name = irSimpleFunction.fqNameWhenAvailable?.shortName()?.asString()
//                ?: error("Not name by: ${irSimpleFunction.dump()}"),
//        )
//
//        arguments[1] = when (val body = irSimpleFunction.body) {
//            is IrBlockBody -> rccIrBodyBlock(body)
//            else -> error("EduError")
//        }
//    }
//
//    fun IrBuilderWithScope.rccIrBodyBlock(
//        expression: IrBlockBody
//    ): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
//        type = rccFqNameImpl.defaultType,
//        constructorSymbol = rccFqNameImpl.owner.primaryConstructor?.symbol
//            ?: error("Not found constructor at: $rccFqNameImpl"),
//    ).apply {
//        arguments[0] = rccElements(
//            elementType = rccIrElement.defaultType,
//            elements = expression.statements.mapTo(mutableListOf()) {
//                it.accept(this@AstTreeTranspilation, Unit)
//            }
//        )
//    }
//
//    fun IrBuilderWithScope.rccFqName(
//        packageName: String,
//        name: String,
//    ): IrConstructorCall = IrConstructorCallImpl.fromSymbolOwner(
//        type = rccFqNameImpl.defaultType,
//        constructorSymbol = rccFqNameImpl.owner.primaryConstructor?.symbol
//            ?: error("Not found constructor at: $rccFqNameImpl"),
//    ).apply {
//        arguments[0] = irString(packageName)
//        arguments[0] = rccName(name)
//    }
//
//    fun IrBuilderWithScope.rccElements(
//        elementType: IrType,
//        elements: List<IrExpression>,
//    ): IrCall = irCall(listOf).apply {
//        this.arguments[0] = irVararg(elementType, elements)
//    }
    override fun visitElement(
        element: IrElement,
        data: IrElement
    ): IrExpression {
        TODO("Not yet implemented")
    }
}

