package foo.bar

import androidx.compose.runtime.Composable
import org.prometey.ast.tree.RccIrTree
import org.prometey.ast.tree.annotation.AstTree
import org.prometey.ast.tree.declarations.RccIrBody
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.expressions.RccIrCall
import org.prometey.ast.tree.visitor.RccIrVisitor

@Composable
@AstTree
fun MainComponent(): String {
    edu()
    edu()
    return ""
}

fun edu() {

}

fun main() {
    val b: RccIrTree = MainComponentAstTree.ast

    b.root.accept(MainScreenVisitor())
}

class MainScreenVisitor : RccIrVisitor<Unit> {

    override fun visitIrFunction(function: RccIrFunction) {
        println("EduLog: ${function.body.elements}")
    }

    override fun visitIrBody(body: RccIrBody) {
        TODO("Not yet implemented")
    }

    override fun visitCall(expression: RccIrCall) {
        TODO("Not yet implemented")
    }

}