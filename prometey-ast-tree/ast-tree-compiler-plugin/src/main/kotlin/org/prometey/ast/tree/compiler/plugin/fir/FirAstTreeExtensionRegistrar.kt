package org.prometey.ast.tree.compiler.plugin.fir

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class FirAstTreeExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::AstTreeResolveExtension
    }
}