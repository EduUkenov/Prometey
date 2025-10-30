package foo.bar

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