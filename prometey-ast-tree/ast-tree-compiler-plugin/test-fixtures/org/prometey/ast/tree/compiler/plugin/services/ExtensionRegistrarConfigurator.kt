package org.prometey.ast.tree.compiler.plugin.services

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices
import org.prometey.ast.tree.compiler.plugin.fir.FirAstTreeExtensionRegistrar
import org.prometey.ast.tree.compiler.plugin.ir.SimpleIrGenerationExtension

class ExtensionRegistrarConfigurator(testServices: TestServices) :
    EnvironmentConfigurator(testServices) {
    override fun CompilerPluginRegistrar.ExtensionStorage.registerCompilerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration
    ) {
        FirExtensionRegistrarAdapter.registerExtension(FirAstTreeExtensionRegistrar())
        IrGenerationExtension.registerExtension(SimpleIrGenerationExtension())
    }
}