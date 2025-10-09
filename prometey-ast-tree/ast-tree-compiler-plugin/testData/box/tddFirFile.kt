package tdd.sample.firP.fileP

import org.prometey.ast.tree.annotation.Ast

val sampleProperty = "SampleProperty"

@Ast
fun sampleOneFunction() {
    sampleTwoFunction()
}

@Ast
fun sampleTwoFunction() {

}