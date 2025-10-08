package org.prometey.ast.tree.gradle.plugin

import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

@Suppress("unused")
class RccAstCompilerGradlePlugin : KotlinCompilerPluginSupportPlugin {
    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        val project = kotlinCompilation.target.project

        return project.provider {
            emptyList()
        }
    }

    override fun getCompilerPluginId(): String = "ast.tree.compiler.plugin"

    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        groupId = "ast.tree.compiler.plugin",
        artifactId = "ast.tree.compiler.plugin",
        version = "0.0.1",
    )

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true
}