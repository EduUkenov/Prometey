package org.prometey.ast.tree.compiler.plugin

import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

class AstTreeCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String = "ast.tree.compiler.plugin"

    override val pluginOptions: Collection<CliOption> = emptyList()

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        //error("Unexpected config option: '${option.optionName}'")
    }
}