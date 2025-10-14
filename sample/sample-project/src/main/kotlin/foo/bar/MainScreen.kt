package foo.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import org.prometey.ast.tree.RccIrTree
import org.prometey.ast.tree.annotation.Ast

@Ast
@Composable
fun MainScreen() {
    Column {
        Box {
            Row {

            }
        }
    }
}

val a = MainScreenAstTree.ast

@Ast
@Composable
fun MainComponent() {
    Column {
        Box {
            Row {

            }
        }
    }
}

fun test() {
    val b: RccIrTree = MainComponentAstTree.ast
    println(b.toString())
}

fun main() {
    test()
}