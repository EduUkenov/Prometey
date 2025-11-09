package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.DeprecatedForRemovalCompilerApi
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.backend.common.lower.createIrBuilder
import org.jetbrains.kotlin.ir.IrBuiltIns
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.builders.IrBuilder
import org.jetbrains.kotlin.ir.builders.irNull
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.builders.irVararg
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrConstructor
import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry
import org.jetbrains.kotlin.ir.declarations.IrExternalPackageFragment
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrReplSnippet
import org.jetbrains.kotlin.ir.declarations.IrScript
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.declarations.IrTypeAlias
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.expressions.IrBlock
import org.jetbrains.kotlin.ir.expressions.IrBlockBody
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.IrBranch
import org.jetbrains.kotlin.ir.expressions.IrBreak
import org.jetbrains.kotlin.ir.expressions.IrBreakContinue
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrCallableReference
import org.jetbrains.kotlin.ir.expressions.IrCatch
import org.jetbrains.kotlin.ir.expressions.IrClassReference
import org.jetbrains.kotlin.ir.expressions.IrComposite
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstantArray
import org.jetbrains.kotlin.ir.expressions.IrConstantObject
import org.jetbrains.kotlin.ir.expressions.IrConstantPrimitive
import org.jetbrains.kotlin.ir.expressions.IrConstantValue
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression
import org.jetbrains.kotlin.ir.expressions.IrContinue
import org.jetbrains.kotlin.ir.expressions.IrDeclarationReference
import org.jetbrains.kotlin.ir.expressions.IrDelegatingConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrDoWhileLoop
import org.jetbrains.kotlin.ir.expressions.IrDynamicExpression
import org.jetbrains.kotlin.ir.expressions.IrDynamicMemberExpression
import org.jetbrains.kotlin.ir.expressions.IrDynamicOperatorExpression
import org.jetbrains.kotlin.ir.expressions.IrElseBranch
import org.jetbrains.kotlin.ir.expressions.IrEnumConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrErrorCallExpression
import org.jetbrains.kotlin.ir.expressions.IrErrorExpression
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody
import org.jetbrains.kotlin.ir.expressions.IrFieldAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference
import org.jetbrains.kotlin.ir.expressions.IrGetClass
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue
import org.jetbrains.kotlin.ir.expressions.IrGetField
import org.jetbrains.kotlin.ir.expressions.IrGetObjectValue
import org.jetbrains.kotlin.ir.expressions.IrGetSingletonValue
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.IrInlinedFunctionBlock
import org.jetbrains.kotlin.ir.expressions.IrInstanceInitializerCall
import org.jetbrains.kotlin.ir.expressions.IrLocalDelegatedPropertyReference
import org.jetbrains.kotlin.ir.expressions.IrLoop
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrPropertyReference
import org.jetbrains.kotlin.ir.expressions.IrRawFunctionReference
import org.jetbrains.kotlin.ir.expressions.IrReturn
import org.jetbrains.kotlin.ir.expressions.IrReturnableBlock
import org.jetbrains.kotlin.ir.expressions.IrRichCallableReference
import org.jetbrains.kotlin.ir.expressions.IrRichFunctionReference
import org.jetbrains.kotlin.ir.expressions.IrRichPropertyReference
import org.jetbrains.kotlin.ir.expressions.IrSetField
import org.jetbrains.kotlin.ir.expressions.IrSetValue
import org.jetbrains.kotlin.ir.expressions.IrSpreadElement
import org.jetbrains.kotlin.ir.expressions.IrStringConcatenation
import org.jetbrains.kotlin.ir.expressions.IrSuspendableExpression
import org.jetbrains.kotlin.ir.expressions.IrSuspensionPoint
import org.jetbrains.kotlin.ir.expressions.IrSyntheticBody
import org.jetbrains.kotlin.ir.expressions.IrThrow
import org.jetbrains.kotlin.ir.expressions.IrTry
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall
import org.jetbrains.kotlin.ir.expressions.IrValueAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrVararg
import org.jetbrains.kotlin.ir.expressions.IrWhen
import org.jetbrains.kotlin.ir.expressions.IrWhileLoop
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.IrDynamicType
import org.jetbrains.kotlin.ir.types.IrErrorType
import org.jetbrains.kotlin.ir.types.IrSimpleType
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.callableId
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.util.getPackageFragment
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.jetbrains.kotlin.ir.visitors.IrVisitor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class AstTreeTranspilation(
    private val pluginContext: AstTreePluginContext
) {
    fun build(astTreeElementContainer: AstTreeElementContainer): IrExpression {
        return astTreeElementContainer.expression.elementAt(0).accept(Visitor(), null)
    }

    private inner class Visitor : IrVisitor<IrExpression, Nothing?>() {
        private lateinit var expression: IrExpression

        override fun visitElement(
            element: IrElement,
            data: Nothing?
        ): IrExpression {
            expression = element.accept(this, null)
            return expression
        }

        override fun visitDeclaration(declaration: IrDeclarationBase, data: Nothing?): IrExpression {
            error("IrDeclarationBase is not supported")
        }

        override fun visitValueParameter(declaration: IrValueParameter, data: Nothing?): IrExpression {
            error("IrValueParameter is not supported")
        }

        override fun visitClass(declaration: IrClass, data: Nothing?): IrExpression {
            error("IrClass is not supported")
        }

        override fun visitAnonymousInitializer(declaration: IrAnonymousInitializer, data: Nothing?): IrExpression {
            error("IrAnonymousInitializer is not supported")
        }

        override fun visitTypeParameter(declaration: IrTypeParameter, data: Nothing?): IrExpression {
            error("IrTypeParameter is not supported")
        }

        override fun visitFunction(declaration: IrFunction, data: Nothing?): IrExpression {
            error("IrFunction is not supported")
        }

        override fun visitConstructor(declaration: IrConstructor, data: Nothing?): IrExpression {
            error("IrConstructor is not supported")
        }

        override fun visitEnumEntry(declaration: IrEnumEntry, data: Nothing?): IrExpression {
            error("IrEnumEntry is not supported")
        }

        override fun visitField(declaration: IrField, data: Nothing?): IrExpression {
            error("IrField is not supported")
        }

        override fun visitLocalDelegatedProperty(declaration: IrLocalDelegatedProperty, data: Nothing?): IrExpression {
            error("IrLocalDelegatedProperty is not supported")
        }

        override fun visitModuleFragment(declaration: IrModuleFragment, data: Nothing?): IrExpression {
            error("IrModuleFragment is not supported")
        }

        override fun visitProperty(declaration: IrProperty, data: Nothing?): IrExpression {
            error("IrProperty is not supported")
        }

        override fun visitScript(declaration: IrScript, data: Nothing?): IrExpression {
            error("IrScript is not supported")
        }

        override fun visitReplSnippet(declaration: IrReplSnippet, data: Nothing?): IrExpression {
            error("IrReplSnippet is not supported")
        }

        @OptIn(DeprecatedForRemovalCompilerApi::class)
        override fun visitSimpleFunction(
            declaration: IrSimpleFunction,
            data: Nothing?
        ): IrExpression {
            //declaration.getPackageFragment()
            val builder = pluginContext.irBuiltIns.createIrBuilder(declaration.symbol)
            return with(builder) {
                rccIrFunctionOf(declaration)
            }
        }

        override fun visitTypeAlias(declaration: IrTypeAlias, data: Nothing?): IrExpression {
            error("IrTypeAlias is not supported")
        }

        override fun visitVariable(declaration: IrVariable, data: Nothing?): IrExpression {
            error("IrVariable is not supported")
        }

        override fun visitPackageFragment(declaration: IrPackageFragment, data: Nothing?): IrExpression {
            error("IrPackageFragment is not supported")
        }

        override fun visitExternalPackageFragment(declaration: IrExternalPackageFragment, data: Nothing?): IrExpression {
            error("IrExternalPackageFragment is not supported")
        }

        override fun visitFile(declaration: IrFile, data: Nothing?): IrExpression {
            error("IrFile is not supported")
        }

        override fun visitExpression(expression: IrExpression, data: Nothing?): IrExpression {
            error("IrExpression is not supported")
        }

        override fun visitBody(body: IrBody, data: Nothing?): IrExpression {
            error("IrBody is not supported")
        }

        override fun visitExpressionBody(body: IrExpressionBody, data: Nothing?): IrExpression {
            error("IrExpressionBody is not supported")
        }

        override fun visitBlockBody(body: IrBlockBody, data: Nothing?): IrExpression {
            error("IrBlockBody is not supported")
        }

        override fun visitDeclarationReference(expression: IrDeclarationReference, data: Nothing?): IrExpression {
            error("IrDeclarationReference is not supported")
        }

        override fun visitMemberAccess(expression: IrMemberAccessExpression<*>, data: Nothing?): IrExpression {
            error("IrMemberAccessExpression is not supported")
        }

        override fun visitFunctionAccess(expression: IrFunctionAccessExpression, data: Nothing?): IrExpression {
            error("IrFunctionAccessExpression is not supported")
        }

        override fun visitConstructorCall(expression: IrConstructorCall, data: Nothing?): IrExpression {
            error("IrConstructorCall is not supported")
        }

        override fun visitSingletonReference(expression: IrGetSingletonValue, data: Nothing?): IrExpression {
            error("IrGetSingletonValue is not supported")
        }

        override fun visitGetObjectValue(expression: IrGetObjectValue, data: Nothing?): IrExpression {
            val builder = pluginContext.irBuiltIns.createIrBuilder(expression.symbol)
            return with(builder) {
                rccIrGetObjectValueOf(expression)
            }

        }

        override fun visitGetEnumValue(expression: IrGetEnumValue, data: Nothing?): IrExpression {
            error("IrGetEnumValue is not supported")
        }

        override fun visitRawFunctionReference(expression: IrRawFunctionReference, data: Nothing?): IrExpression {
            error("IrRawFunctionReference is not supported")
        }

        override fun visitContainerExpression(expression: IrContainerExpression, data: Nothing?): IrExpression {
            error("IrContainerExpression is not supported")
        }

        override fun visitBlock(expression: IrBlock, data: Nothing?): IrExpression {
            error("IrBlock is not supported")
        }

        override fun visitComposite(expression: IrComposite, data: Nothing?): IrExpression {
            error("IrComposite is not supported")
        }

        override fun visitReturnableBlock(expression: IrReturnableBlock, data: Nothing?): IrExpression {
            error("IrReturnableBlock is not supported")
        }

        override fun visitInlinedFunctionBlock(inlinedBlock: IrInlinedFunctionBlock, data: Nothing?): IrExpression {
            error("IrInlinedFunctionBlock is not supported")
        }

        override fun visitSyntheticBody(body: IrSyntheticBody, data: Nothing?): IrExpression {
            error("IrSyntheticBody is not supported")
        }

        override fun visitBreakContinue(jump: IrBreakContinue, data: Nothing?): IrExpression {
            error("IrBreakContinue is not supported")
        }

        override fun visitBreak(jump: IrBreak, data: Nothing?): IrExpression {
            error("IrBreak is not supported")
        }

        override fun visitContinue(jump: IrContinue, data: Nothing?): IrExpression {
            error("IrContinue is not supported")
        }

        override fun visitCall(expression: IrCall, data: Nothing?): IrExpression {
            val builder = pluginContext.irBuiltIns.createIrBuilder(expression.symbol)
            return with(builder) {
                rccIrCallOf(expression)
            }
        }

        override fun visitCallableReference(expression: IrCallableReference<*>, data: Nothing?): IrExpression {
            error("IrCallableReference is not supported")
        }

        override fun visitFunctionReference(expression: IrFunctionReference, data: Nothing?): IrExpression {
            error("IrFunctionReference is not supported")
        }

        override fun visitPropertyReference(expression: IrPropertyReference, data: Nothing?): IrExpression {
            error("IrPropertyReference is not supported")
        }

        override fun visitLocalDelegatedPropertyReference(expression: IrLocalDelegatedPropertyReference, data: Nothing?): IrExpression {
            error("IrLocalDelegatedPropertyReference is not supported")
        }

        override fun visitRichCallableReference(expression: IrRichCallableReference<*>, data: Nothing?): IrExpression {
            error("IrRichCallableReference is not supported")
        }

        override fun visitRichFunctionReference(expression: IrRichFunctionReference, data: Nothing?): IrExpression {
            error("IrRichFunctionReference is not supported")
        }

        override fun visitRichPropertyReference(expression: IrRichPropertyReference, data: Nothing?): IrExpression {
            error("IrRichPropertyReference is not supported")
        }

        override fun visitClassReference(expression: IrClassReference, data: Nothing?): IrExpression {
            error("IrClassReference is not supported")
        }

        override fun visitConst(expression: IrConst, data: Nothing?): IrExpression {
            val builder = pluginContext.irBuiltIns

            return with(builder) {
                rccIrConstOf(expression)
            }
        }

        override fun visitConstantValue(expression: IrConstantValue, data: Nothing?): IrExpression {
            error("IrConstantValue is not supported")
        }

        override fun visitConstantPrimitive(expression: IrConstantPrimitive, data: Nothing?): IrExpression {
            error("IrConstantPrimitive is not supported")
        }

        override fun visitConstantObject(expression: IrConstantObject, data: Nothing?): IrExpression {
            error("IrConstantObject is not supported")
        }

        override fun visitConstantArray(expression: IrConstantArray, data: Nothing?): IrExpression {
            error("IrConstantArray is not supported")
        }

        override fun visitDelegatingConstructorCall(expression: IrDelegatingConstructorCall, data: Nothing?): IrExpression {
            error("IrDelegatingConstructorCall is not supported")
        }

        override fun visitDynamicExpression(expression: IrDynamicExpression, data: Nothing?): IrExpression {
            error("IrDynamicExpression is not supported")
        }

        override fun visitDynamicOperatorExpression(expression: IrDynamicOperatorExpression, data: Nothing?): IrExpression {
            error("IrDynamicOperatorExpression is not supported")
        }

        override fun visitDynamicMemberExpression(expression: IrDynamicMemberExpression, data: Nothing?): IrExpression {
            error("IrDynamicMemberExpression is not supported")
        }

        override fun visitEnumConstructorCall(expression: IrEnumConstructorCall, data: Nothing?): IrExpression {
            error("IrEnumConstructorCall is not supported")
        }

        override fun visitErrorExpression(expression: IrErrorExpression, data: Nothing?): IrExpression {
            error("IrErrorExpression is not supported")
        }

        override fun visitErrorCallExpression(expression: IrErrorCallExpression, data: Nothing?): IrExpression {
            error("IrErrorCallExpression is not supported")
        }

        override fun visitFieldAccess(expression: IrFieldAccessExpression, data: Nothing?): IrExpression {
            error("IrFieldAccessExpression is not supported")
        }

        override fun visitGetField(expression: IrGetField, data: Nothing?): IrExpression {
            error("IrGetField is not supported")
        }

        override fun visitSetField(expression: IrSetField, data: Nothing?): IrExpression {
            error("IrSetField is not supported")
        }

        override fun visitFunctionExpression(expression: IrFunctionExpression, data: Nothing?): IrExpression {
            val builder = pluginContext.irBuiltIns.createIrBuilder(expression.function.symbol)
            return with(builder) {
                rccIrFunctionExpressionOf(expression)
            }
        }

        override fun visitGetClass(expression: IrGetClass, data: Nothing?): IrExpression {
            error("IrGetClass is not supported")
        }

        override fun visitInstanceInitializerCall(expression: IrInstanceInitializerCall, data: Nothing?): IrExpression {
            error("IrInstanceInitializerCall is not supported")
        }

        override fun visitLoop(loop: IrLoop, data: Nothing?): IrExpression {
            error("IrLoop is not supported")
        }

        override fun visitWhileLoop(loop: IrWhileLoop, data: Nothing?): IrExpression {
            error("IrWhileLoop is not supported")
        }

        override fun visitDoWhileLoop(loop: IrDoWhileLoop, data: Nothing?): IrExpression {
            error("IrDoWhileLoop is not supported")
        }

        override fun visitReturn(expression: IrReturn, data: Nothing?): IrExpression {
            val builder = pluginContext.irBuiltIns.createIrBuilder(expression.returnTargetSymbol)
            return with(builder) {
                rccIrReturnOf(expression)
            }
        }

        override fun visitStringConcatenation(expression: IrStringConcatenation, data: Nothing?): IrExpression {
            error("IrStringConcatenation is not supported")
        }

        override fun visitSuspensionPoint(expression: IrSuspensionPoint, data: Nothing?): IrExpression {
            error("IrSuspensionPoint is not supported")
        }

        override fun visitSuspendableExpression(expression: IrSuspendableExpression, data: Nothing?): IrExpression {
            error("IrSuspendableExpression is not supported")
        }

        override fun visitThrow(expression: IrThrow, data: Nothing?): IrExpression {
            error("IrThrow is not supported")
        }

        override fun visitTry(aTry: IrTry, data: Nothing?): IrExpression {
            error("IrTry is not supported")
        }

        override fun visitCatch(aCatch: IrCatch, data: Nothing?): IrExpression {
            error("IrCatch is not supported")
        }

        override fun visitTypeOperator(expression: IrTypeOperatorCall, data: Nothing?): IrExpression {
            error("IrTypeOperatorCall is not supported")
        }

        override fun visitValueAccess(expression: IrValueAccessExpression, data: Nothing?): IrExpression {
            error("IrValueAccessExpression is not supported")
        }

        override fun visitGetValue(expression: IrGetValue, data: Nothing?): IrExpression {
            error("IrGetValue is not supported")
        }

        override fun visitSetValue(expression: IrSetValue, data: Nothing?): IrExpression {
            error("IrSetValue is not supported")
        }

        override fun visitVararg(expression: IrVararg, data: Nothing?): IrExpression {
            error("IrVararg is not supported")
        }

        override fun visitSpreadElement(spread: IrSpreadElement, data: Nothing?): IrExpression {
            error("IrSpreadElement is not supported")
        }

        override fun visitWhen(expression: IrWhen, data: Nothing?): IrExpression {
            error("IrWhen is not supported")
        }

        override fun visitBranch(branch: IrBranch, data: Nothing?): IrExpression {
            error("IrBranch is not supported")
        }

        override fun visitElseBranch(branch: IrElseBranch, data: Nothing?): IrExpression {
            error("IrElseBranch is not supported")
        }

        private fun IrBuilder.rccIrElementsOf(
            elements: List<IrExpression>
        ): IrCall = IrCallImpl.fromSymbolOwner(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            symbol = pluginContext.listOfRef
        ).apply {
            arguments[0] = irVararg(pluginContext.rccIrElementRef.defaultType, elements)
        }

        private fun IrBuilder.rccIrNameOf(
            name: Name
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrNameImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrNameImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            // Todo надо испривать
            arguments[0] = if (name.isSpecial) irString("") else irString(name.identifier)
        }

        private fun IrBuilder.rccIrFqNameOf(
            fqName: FqName
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrFqNameImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrFqNameImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = irString(fqName.asString())
            arguments[1] = rccIrNameOf(fqName.shortName())
        }

        private fun DeclarationIrBuilder.rccIrTypeOf(
            irType: IrType
        ): IrConstructorCall {
            return when (irType) {
                is IrDynamicType -> error("It is not supported yet IrDynamicType")
                is IrErrorType -> error("It is not supported yet IrErrorType")
                is IrSimpleType -> rccIrTypeSimpleOf(irType)
            }
        }

        private fun DeclarationIrBuilder.rccIrTypeSimpleOf(
            irSimpleType: IrSimpleType
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrTypeSimpleImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrTypeSimpleImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = rccIrFqNameOf(irSimpleType.classFqName!!)
        }

        private fun DeclarationIrBuilder.rccIrFunctionOf(
            irSimpleFunction: IrSimpleFunction,
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrFunctionImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrFunctionImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = rccIrFqNameOf(irSimpleFunction.fqNameWhenAvailable ?: FqName(""))
            arguments[1] = rccIrBodyOf(irSimpleFunction.body)
        }

        private fun DeclarationIrBuilder.rccIrBodyOf(
            irBody: IrBody?
        ): IrExpression {
            return when (irBody) {
                is IrBlockBody -> rccIrBodyBlockOf(irBody)
                is IrExpressionBody -> error("It is not supported yet IrExpressionBody")
                is IrSyntheticBody -> error("It is not supported yet IrSyntheticBody")
                null -> irNull()  // Todo: Тело библиотечных функций не видит
            }
        }

        private fun DeclarationIrBuilder.rccIrBodyBlockOf(
            irBody: IrBlockBody
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrBodyImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrBodyImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = rccIrElementsOf(irBody.statements.map { it.accept(this@Visitor, null) })
        }

        private fun DeclarationIrBuilder.rccIrCallOf(
            irCall: IrCall
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrCallImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrCallImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = rccIrFunctionOf(irCall.symbol.owner)
            arguments[1] = rccIrElementsOf(irCall.arguments.map { it?.accept(this@Visitor, null) ?: irNull() })
        }

        private fun DeclarationIrBuilder.rccIrFunctionExpressionOf(
            irFunctionExpression: IrFunctionExpression
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrFunctionExpressionRef.defaultType,
            constructorSymbol = pluginContext.rccIrFunctionExpressionRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = rccIrFunctionOf(irFunctionExpression.function)
        }

        private fun DeclarationIrBuilder.rccIrReturnOf(
            irReturn: IrReturn
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrReturnImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrReturnImplRef.owner.primaryConstructor!!.symbol,
        ).apply {

        }

        private fun DeclarationIrBuilder.rccIrGetObjectValueOf(
            irGetObjectValue: IrGetObjectValue,
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrGetObjectValueImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrGetObjectValueImplRef.owner.primaryConstructor!!.symbol,
        ).apply {

        }

        private fun IrBuiltIns.rccIrConstOf(
            irConst: IrConst,
        ): IrConstructorCall = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = pluginContext.rccIrConstImplRef.defaultType,
            constructorSymbol = pluginContext.rccIrConstImplRef.owner.primaryConstructor!!.symbol,
        ).apply {

        }
    }
}