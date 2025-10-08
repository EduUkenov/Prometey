package org.prometey.ast.tree

val MainScreenTree = tree {
    rccIrFunction(
        name = "androidx.compose.foundation.layout.Column"
    ) {
        parameters = buildList {
            rccLambda {
                returnType = rccIrType()
                body = rccIrBody {
                    rccIrFunction("androidx.compose.foundation.layout.Box")
                    rccIrFunction("androidx.compose.foundation.layout.Box")
                }
            }
        }
    }
}

@Suppress("BuildListAdds")
fun main() {

}
