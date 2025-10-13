package org.prometey.ast.tree.compiler.plugin.testing

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.ir.declarations.IrConstructor
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.symbols.impl.IrFieldSymbolImpl
import org.prometey.ast.tree.compiler.plugin.AstTreeGeneratedKey

class SimpleIrBodyGenerator(pluginContext: IrPluginContext) :
    AbstractTransformerForGenerator(pluginContext) {
    override fun interestedIn(key: GeneratedDeclarationKey?): Boolean {
        return key == AstTreeGeneratedKey
    }

    override fun visitProperty(declaration: IrProperty) {
        val origin = declaration.origin
        if (origin !is IrDeclarationOrigin.GeneratedByPlugin || !interestedIn(origin.pluginKey)) {
            // не наш плагин → просто обойти дальше
            return super.visitProperty(declaration)
        }

        // если уже есть инициализация — пропускаем
        val backingField = declaration.backingField
        if (backingField?.initializer != null) {
            return super.visitProperty(declaration)
        }

        // создаём константу ""
        val constEmptyString = IrConstImpl.string(
            startOffset = -1,
            endOffset = -1,
            type = irBuiltIns.stringType,
            value = ""
        )

        // если поле есть — добавляем инициализатор
        if (backingField != null) {
            backingField.initializer = irFactory.createExpressionBody(-1, -1, constEmptyString)
        } else {
            // если нет — создаём новое бэкинг-поле
            val field = irFactory.createField(
                startOffset = -1,
                endOffset = -1,
                origin = declaration.origin,
                name = declaration.name,
                type = irBuiltIns.stringType,
                visibility = DescriptorVisibilities.PRIVATE,
                symbol = IrFieldSymbolImpl(),
                isFinal = true,
                isStatic = false,
                isExternal = false,
            ).apply {
                parent = declaration.parent
                initializer = irFactory.createExpressionBody(-1, -1, constEmptyString)
            }

            declaration.backingField = field
        }

        super.visitProperty(declaration)
    }


//    override fun generateBodyForFunction(
//        function: IrSimpleFunction,
//        key: GeneratedDeclarationKey?
//    ): IrBody? {
//        TODO("Not yet implemented")
//    }

//    override fun generateBodyForFunction(function: IrSimpleFunction, key: GeneratedDeclarationKey?): IrBody {
//        require(function.name == SimpleClassGenerator.FOO_ID.callableName)
//        val const = IrConstImpl(-1, -1, irBuiltIns.stringType, IrConstKind.String, value = "Hello world")
//        val returnStatement = IrReturnImpl(-1, -1, irBuiltIns.nothingType, function.symbol, const)
//        return irFactory.createBlockBody(-1, -1, listOf(returnStatement))
//    }

    override fun generateBodyForConstructor(
        constructor: IrConstructor,
        key: GeneratedDeclarationKey?
    ): IrBody? {
        return generateBodyForDefaultConstructor(constructor)
    }
}