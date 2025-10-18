package org.prometey.ast.tree.compiler.plugin.arhive

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.createExpressionBody
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeGeneratedKey

class AstTreeForBodyGeneratorV2(
    val pluginContext: IrPluginContext,
) : IrVisitorVoid() {

    val rccIrTreeImplRef = pluginContext.referenceClass()

    fun lower(moduleFragment: IrModuleFragment) {
        moduleFragment.acceptChildrenVoid(Visitor())
    }

    private inner class Visitor : IrVisitorVoid() {
        override fun visitElement(element: IrElement) {
            element.acceptChildrenVoid(this)
        }

        override fun visitProperty(declaration: IrProperty) {
            val origin = declaration.origin
            if (origin !is IrDeclarationOrigin.GeneratedByPlugin || origin.pluginKey != AstTreeGeneratedKey) return

//            declaration.backingField?.initializer = pluginContext.irFactory.createExpressionBody(
//                expression = IrConstructorCallImpl.fromSymbolOwner(
//                    type = rccIrTreeImpl.defaultType,
//                    constructorSymbol = rccIrTreeImpl.owner.primaryConstructor?.symbol
//                        ?: error("Not found constructor at: $rccIrTreeImpl}"),
//                )
//            )
        }
    }


}