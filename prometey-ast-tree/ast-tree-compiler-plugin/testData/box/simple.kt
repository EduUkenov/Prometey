package foo.bar

import org.prometey.ast.tree.annotation.AstTree

@AstTree()
fun box(): String {
    box1()
    box1()

    return ""
}

fun box1() {

}