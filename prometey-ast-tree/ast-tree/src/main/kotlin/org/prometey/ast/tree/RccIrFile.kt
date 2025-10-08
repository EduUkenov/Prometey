package org.prometey.ast.tree

abstract class RccIrFile : RccIrElement {
    abstract val imports: List<RccIrImport>
    abstract val elements: List<RccIrElement>
}