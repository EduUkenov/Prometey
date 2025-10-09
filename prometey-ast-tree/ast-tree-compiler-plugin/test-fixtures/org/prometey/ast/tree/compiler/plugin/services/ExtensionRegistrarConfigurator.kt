package org.prometey.ast.tree.compiler.plugin.services

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices
import org.prometey.ast.tree.compiler.plugin.fir.AstTreeResolveExtension
import org.prometey.ast.tree.compiler.plugin.service.AstTreeCollectPredicateProvider

class ExtensionRegistrarConfigurator(testServices: TestServices) :
    EnvironmentConfigurator(testServices) {
    override fun CompilerPluginRegistrar.ExtensionStorage.registerCompilerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration
    ) {
        FirExtensionRegistrarAdapter.registerExtension(TddFirExtensionRegistrar())
    }
}

private class TddFirExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        //+::TddFirFileTdd
        +::AstTreeResolveExtension
        +::AstTreeCollectPredicateProvider
    }
}
