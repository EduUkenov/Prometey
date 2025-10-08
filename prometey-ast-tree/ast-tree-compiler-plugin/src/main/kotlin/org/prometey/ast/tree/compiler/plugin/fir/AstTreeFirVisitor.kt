package org.prometey.ast.tree.compiler.plugin.fir

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirProperty
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.declarations.FirSimpleFunction
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid

class AstTreeVisitor(
    private val session: FirSession
) : FirVisitorVoid() {

    override fun visitElement(element: FirElement) {
        element.acceptChildren(this)
    }

    override fun visitRegularClass(regularClass: FirRegularClass) {
        super.visitRegularClass(regularClass)
    }

    override fun visitSimpleFunction(simpleFunction: FirSimpleFunction) {
        println("EduLog: ${simpleFunction.name}")
        super.visitSimpleFunction(simpleFunction)
    }

    override fun visitProperty(property: FirProperty) {
        super.visitProperty(property)
    }


}