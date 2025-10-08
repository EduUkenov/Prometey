package org.prometey.description.compiler.plugin

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.prometey.description.compiler.plugin.ir.DescriptIrGenerationExtension

class DescriptPluginComponentRegistrar : CompilerPluginRegistrar() {
	override val supportsK2: Boolean = true
	
	override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {

		IrGenerationExtension.registerExtension(DescriptIrGenerationExtension())
	}
}