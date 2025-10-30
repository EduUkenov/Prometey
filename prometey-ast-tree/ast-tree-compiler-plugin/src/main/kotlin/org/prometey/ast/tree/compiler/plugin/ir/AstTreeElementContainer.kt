package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeAnnotations

/**
 * Responsible for finding source expressions that have the @AstTree annotation.
 */
class AstTreeElementContainer() {
    private val _expressions: MutableSet<IrElement> = mutableSetOf()
    val expression: Set<IrElement> get() = _expressions

    fun lower(moduleFragment: IrModuleFragment) {
        moduleFragment.acceptChildrenVoid(Visit())
    }

    private inner class Visit() : IrVisitorVoid() {
        override fun visitElement(element: IrElement) {
            element.acceptChildrenVoid(this)
        }

        override fun visitClass(declaration: IrClass) {
            if (!declaration.hasAnnotation(AstTreeAnnotations.astTreeAnnotationClassId)) return

            _expressions += declaration
        }

        override fun visitSimpleFunction(declaration: IrSimpleFunction) {
            if (!declaration.hasAnnotation(AstTreeAnnotations.astTreeAnnotationClassId)) return

            _expressions += declaration
        }
    }
}