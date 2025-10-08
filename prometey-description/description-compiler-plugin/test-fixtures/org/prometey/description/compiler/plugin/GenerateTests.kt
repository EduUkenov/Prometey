package org.prometey.description.compiler.plugin

import org.jetbrains.kotlin.generators.generateTestGroupSuiteWithJUnit5
import org.prometey.description.compiler.plugin.runners.AbstractJvmBoxTest

fun main() {
	generateTestGroupSuiteWithJUnit5 {
		testGroup(
			testDataRoot = "prometey-description/description-compiler-plugin/testData",
			testsRoot = "prometey-description/description-compiler-plugin/test-gen"
		) {
			testClass<AbstractJvmBoxTest> {
				model("box")
			}
		}
	}
}
