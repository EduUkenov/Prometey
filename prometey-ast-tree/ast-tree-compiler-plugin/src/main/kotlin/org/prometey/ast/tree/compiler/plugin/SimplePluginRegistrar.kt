package org.prometey.ast.tree.compiler.plugin

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar
import org.prometey.ast.tree.compiler.plugin.fir.AstTreeResolveExtension
import org.prometey.ast.tree.compiler.plugin.service.AstTreeCollectPredicateProvider

class SimplePluginRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::AstTreeResolveExtension
        +::AstTreeCollectPredicateProvider
    }
}
