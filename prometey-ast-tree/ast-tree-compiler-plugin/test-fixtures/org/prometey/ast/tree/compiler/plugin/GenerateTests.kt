package org.prometey.ast.tree.compiler.plugin

import org.jetbrains.kotlin.generators.generateTestGroupSuiteWithJUnit5
import org.prometey.ast.tree.compiler.plugin.runners.AbstractJvmBoxTest

fun main() {
    generateTestGroupSuiteWithJUnit5 {
        testGroup(
            testDataRoot = "prometey-ast-tree/ast-tree-compiler-plugin/testData",
            testsRoot = "prometey-ast-tree/ast-tree-compiler-plugin/test-gen"
        ) {

            testClass<AbstractJvmBoxTest> {
                model("box")
            }
        }
    }
}
