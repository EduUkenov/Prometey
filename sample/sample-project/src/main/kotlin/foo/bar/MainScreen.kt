package foo.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.prometey.ast.tree.RccIrTree
import org.prometey.ast.tree.annotation.AstTree
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.expressions.RccIrCall
import org.prometey.ast.tree.expressions.RccIrFunctionExpression
import org.prometey.ast.tree.visitor.RccIrVisitor

@AstTree
@Composable
fun MainComponent() {
    Column(
        modifier = Modifier.size(1.dp)
    ) {
        Box() {

        }
    }
}

fun edu() {

}

fun main() {
    val b: RccIrTree = MainComponentAstTree.ast

    b.root.accept(MainScreenVisitor())
}

class MainScreenVisitor : RccIrVisitor<Unit> {
    override fun visitIrFunction(function: RccIrFunction) {
        println("EduLog function: ${function.fqName.packageName}")

        function.body?.elements?.forEach {
            if (it is RccIrCall) {
                val element = it.element

                if (element is RccIrFunction) {
                    println("EduLog: ${element.fqName.name.value}")
                }

                it.valueArguments.forEach {
                    if (it is RccIrFunctionExpression) {
                        println("EduLog 1: ${it.function.fqName.name.value}")
                    }
                }
            }
        }
    }
}
