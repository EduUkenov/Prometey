package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.prometey.ast.tree.compiler.plugin.AstTreeGeneratedKey

class AstTreeForBodyGenerator(
    pluginContext: IrPluginContext
) : IrVisitorVoid() {

    override fun visitProperty(declaration: IrProperty) {
        println("EduLog ${declaration.origin}")
        if (declaration.origin == AstTreeGeneratedKey) {
            println("EduLog")
        } else return
    }

    override fun visitField(declaration: IrField) {
        if (declaration.origin == AstTreeGeneratedKey) {
            println("EduLog")
        } else return
    }
}