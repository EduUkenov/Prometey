package org.prometey.ast.tree.compiler.plugin.tdd

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.FirSimpleFunction
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid

class TddFirVisitor : FirVisitorVoid() {

    override fun visitElement(element: FirElement) {
        element.acceptChildren(this)
    }

    override fun visitFunction(function: FirFunction) {
        println("------------- Visit File -------------")
        println("${function.annotations}")
        println("${function.moduleData.name}")
        println("${function.moduleData.dependencies}")
    }

    override fun visitSimpleFunction(simpleFunction: FirSimpleFunction) {
        println("------------- visitSimpleFunction -------------")
        println("${simpleFunction.name}")
        println("${simpleFunction.annotations}")
        println("${simpleFunction.moduleData.name}")
        println("${simpleFunction.moduleData.dependencies}")
    }

    override fun visitFile(file: FirFile) {
        println("------------- Visit File -------------")
        println("${file.annotations}")
        println("${file.moduleData.name}")
        println("${file.moduleData.dependencies}")
    }
}