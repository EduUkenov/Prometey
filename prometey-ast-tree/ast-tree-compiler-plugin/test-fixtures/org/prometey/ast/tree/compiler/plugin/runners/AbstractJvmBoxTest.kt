package org.prometey.ast.tree.compiler.plugin.runners

import org.jetbrains.kotlin.test.FirParser
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.directives.CodegenTestDirectives
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives
import org.jetbrains.kotlin.test.directives.JvmEnvironmentConfigurationDirectives
import org.jetbrains.kotlin.test.runners.codegen.AbstractFirBlackBoxCodegenTestBase
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider
import org.prometey.ast.tree.compiler.plugin.services.ExtensionRegistrarConfigurator
import org.prometey.ast.tree.compiler.plugin.services.PluginAnnotationsProvider

open class AbstractJvmBoxTest : AbstractFirBlackBoxCodegenTestBase(FirParser.LightTree) {
    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider {
        return EnvironmentBasedStandardLibrariesPathProvider
    }

    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)

        with(builder) {
            defaultDirectives {
                +CodegenTestDirectives.DUMP_IR
                +FirDiagnosticsDirectives.FIR_DUMP
                +JvmEnvironmentConfigurationDirectives.FULL_JDK
                +CodegenTestDirectives.IGNORE_DEXING // Avoids loading R8 from the classpath.
            }

            useConfigurators(
                ::PluginAnnotationsProvider,
                ::ExtensionRegistrarConfigurator
            )
        }
    }
}
