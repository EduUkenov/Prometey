package foo.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.RccIrTree
import org.prometey.ast.tree.annotation.Ast
import org.prometey.ast.tree.visitor.RccIrVisitor

//@Ast
//@Composable
//fun MainComponent() {
//    Column {
//        Box {
//            Row {
//
//            }
//        }
//    }
//}
//
//fun test() {
//    val b: RccIrTree = MainComponentAstTree.ast
//
//    b.root.accept(Edu())
//}

fun main() {
    listOf(
        1,
        2
    )
}

//class Edu : RccIrVisitor<Unit> {
//    override fun visitIrFunction(function: RccIrFunction) {
//        println("EduLog ${function.fqName}")
//    }
//}