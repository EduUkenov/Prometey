package org.prometey.ast.tree.compiler.plugin.services

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices
import org.prometey.ast.tree.compiler.plugin.AstTreePluginComponentRegistrar
import org.prometey.ast.tree.compiler.plugin.SimplePluginRegistrar

class ExtensionRegistrarConfigurator(testServices: TestServices) :
    EnvironmentConfigurator(testServices) {
    override fun CompilerPluginRegistrar.ExtensionStorage.registerCompilerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration
    ) {
        FirExtensionRegistrarAdapter.registerExtension(SimplePluginRegistrar())
        //IrGenerationExtension.registerExtension(SimpleIrGenerationExtension())
    }
}


