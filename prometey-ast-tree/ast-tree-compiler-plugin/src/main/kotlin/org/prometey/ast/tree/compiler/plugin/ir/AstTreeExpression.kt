package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeAnnotations

/**
 * Responsible for finding source expressions that have the @AstTree annotation.
 */
class AstTreeExpression() {
    private val _expressions: MutableMap<String, IrElement> = mutableMapOf()

    fun lower(moduleFragment: IrModuleFragment) {
        moduleFragment.acceptChildrenVoid(Visit(_expressions))
    }

    private class Visit(
        private val store: MutableMap<String, IrElement>
    ) : IrVisitorVoid() {
        override fun visitElement(element: IrElement) {
            element.acceptChildrenVoid(this)
        }

        override fun visitClass(declaration: IrClass) {
            if (!declaration.hasAnnotation(AstTreeAnnotations.astTreeAnnotationClassId)) return

            store[""] = declaration
        }

        override fun visitSimpleFunction(declaration: IrSimpleFunction) {
            if (!declaration.hasAnnotation(AstTreeAnnotations.astTreeAnnotationClassId)) return

            declaration.returnType.classFqName
            store[""] = declaration
        }
    }
}