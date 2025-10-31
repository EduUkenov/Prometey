package foo.bar

import androidx.compose.runtime.Composable
import org.prometey.ast.tree.RccIrTree
import org.prometey.ast.tree.annotation.AstTree
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.visitor.RccIrVisitor

@AstTree
@Composable
fun MainComponent() {

}

fun test() {
    val b: RccIrTree = MainComponentAstTree.ast

    b.root.accept(Edu())
}

fun main() {
    test()
}

class Edu : RccIrVisitor<Unit> {
    override fun visitIrFunction(function: RccIrFunction) {
        println("EduLog ${function.fqName.name.value}")
    }
}