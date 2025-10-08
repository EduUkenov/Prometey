plugins {
	kotlin("jvm")
	id("java-gradle-plugin")
	`maven-publish`
}

group = "ast.tree.gradle.plugin"
version = "0.0.1"

gradlePlugin {
	plugins {
		create("ast") {
			id = "ast.tree.gradle.plugin"
			implementationClass = "org.prometey.ast.tree.gradle.plugin.RccAstCompilerGradlePlugin"
		}
	}
}

dependencies {
	implementation(kotlin("gradle-plugin-api"))
}
