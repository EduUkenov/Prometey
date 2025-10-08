package org.prometey.description.compiler.plugin

import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

class DescriptCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String = "kotlinmeta.compiler.plugin"

    override val pluginOptions: Collection<CliOption> = emptyList()

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        //error("Unexpected config option: '${option.optionName}'")
    }
}