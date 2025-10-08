package foo.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
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
    val a = MainScreenAstTree
    val b = MainComponentAstTree
}

fun main() {
    test()
}