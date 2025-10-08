package foo.bar

import org.prometey.ast.tree.annotation.Ast

@Ast
fun box(): String {
    val result = MyClass().foo()
    return if (result == "Hello world") { "OK" } else { "Fail: $result" }
}