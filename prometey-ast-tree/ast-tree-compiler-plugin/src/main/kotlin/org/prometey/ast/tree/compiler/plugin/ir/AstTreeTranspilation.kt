package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.builders.IrBuilder
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.IrDynamicType
import org.jetbrains.kotlin.ir.types.IrErrorType
import org.jetbrains.kotlin.ir.types.IrSimpleType
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.jetbrains.kotlin.ir.visitors.IrVisitor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeClassIds

// val fqName: RccFqName
// val parametersType: List<RccIrType>
// val returnType: RccIrType
// val extensionType: RccIrType?
// val body: RccIrBody

class AstTreeTranspilation(
    pluginContext: IrPluginContext
) {
    private val rccIrNameImplRef = pluginContext.referenceClass(AstTreeClassIds.rccNameImpl)!!
    private val rccIrTypeFqNameImplRef = pluginContext.referenceClass(AstTreeClassIds.rccFqNameImpl)!!
    private val rccIrFunctionImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrFunctionImpl)!!
    private val rccIrTypeSimpleImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrTypeSimpleImpl)!!

    fun build(astTreeElementContainer: AstTreeElementContainer): IrExpression {
        return astTreeElementContainer.expression.elementAt(0).accept(Visitor(), null)
    }

    private inner class Visitor : IrVisitor<IrExpression, Nothing?>() {
        lateinit var expression: IrExpression

        override fun visitElement(
            element: IrElement,
            data: Nothing?
        ): IrExpression {
            expression = element.accept(this, null)
            return expression
        }

        override fun visitSimpleFunction(
            declaration: IrSimpleFunction,
            data: Nothing?
        ): IrExpression {
            return declaration.rccIrFunctionConstructor()
        }

        context(builder: IrBuilder)
        private fun Name.rccIrNameConstructor(): IrExpression = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = rccIrNameImplRef.defaultType,
            constructorSymbol = rccIrTypeSimpleImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = builder.irString(identifier)
        }

        context(builder: DeclarationIrBuilder)
        private fun FqName.rccIrFqNameConstructor(): IrExpression = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = rccIrTypeFqNameImplRef.defaultType,
            constructorSymbol = rccIrTypeSimpleImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = TODO()
        }

        context(builder: DeclarationIrBuilder)
        private fun IrType.rccIrTypeConstructor(): IrExpression {
            return when (this) {
                is IrDynamicType -> error("It is not supported yet IrDynamicType")
                is IrErrorType -> error("It is not supported yet IrErrorType")
                is IrSimpleType -> rccIrTypeSimpleConstructor()
            }
        }

        context(builder: DeclarationIrBuilder)
        private fun IrSimpleType.rccIrTypeSimpleConstructor(): IrExpression = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = rccIrTypeSimpleImplRef.defaultType,
            constructorSymbol = rccIrTypeSimpleImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = TODO()
        }

        fun IrSimpleFunction.rccIrFunctionConstructor(): IrExpression = IrConstructorCallImpl.Companion.fromSymbolOwner(
            type = rccIrFunctionImplRef.defaultType,
            constructorSymbol = rccIrFunctionImplRef.owner.primaryConstructor!!.symbol,
        ).apply {
            arguments[0] = TODO()
        }
    }
}