package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

class AstTreeIrGenerationExtension : IrGenerationExtension {
    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        //val transformers2 = listOf(AstTreeForBodyGenerator(AstTreeContext(pluginContext)))

//        for (transformer in transformers2) {
//            moduleFragment.acceptVoid(transformer)
//        }
    }
}
