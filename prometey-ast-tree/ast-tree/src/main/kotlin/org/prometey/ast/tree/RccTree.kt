package org.prometey.ast.tree

class RccTree {

}

fun tree(
    block: RccTree.() -> Unit
): RccTree = RccTree().apply(block)

fun main() {
    tree {

    }
}