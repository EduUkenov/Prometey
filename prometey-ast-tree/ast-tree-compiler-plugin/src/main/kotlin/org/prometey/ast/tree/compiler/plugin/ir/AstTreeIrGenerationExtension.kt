package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid

class AstTreeIrGenerationExtension : IrGenerationExtension {
    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext
    ) {
        val context = AstTreePluginContext(pluginContext)
        val elementContainer = AstTreeElementContainer().apply {
            lower(moduleFragment)
        }

        AstTreeBodyGenerator(context).lower(moduleFragment, elementContainer)
    }
}