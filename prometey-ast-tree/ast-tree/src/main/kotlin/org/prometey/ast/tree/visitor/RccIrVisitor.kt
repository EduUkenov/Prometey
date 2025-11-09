package org.prometey.ast.tree.visitor

import org.prometey.ast.tree.declarations.RccIrBody
import org.prometey.ast.tree.expressions.RccIrCall
import org.prometey.ast.tree.declarations.RccIrFunction
import org.prometey.ast.tree.expressions.RccIrConst
import org.prometey.ast.tree.expressions.RccIrFunctionExpression
import org.prometey.ast.tree.expressions.RccIrGetObjectValue
import org.prometey.ast.tree.expressions.RccIrReturn

interface RccIrVisitor<R> {
    fun visitIrFunction(function: RccIrFunction): R {
        error("Not Implementation visitIrFunction")
    }

    fun visitIrBody(body: RccIrBody): R {
        error("Not Implementation visitIrBody")
    }

    fun visitCall(expression: RccIrCall): R {
        error("Not Implementation visitCall")
    }

    fun visitReturn(expression: RccIrReturn): R {
        error("Not Implementation visitReturn")
    }

    fun visitIrFunctionExpression(expression: RccIrFunctionExpression): R {
        error("Not Implementation visitIrFunctionExpression")
    }

    fun visitIrGetObjectValue(expression: RccIrGetObjectValue): R {
        error("Not Implementation visitIrGetObjectValue")
    }

    fun visitIrConst(expression: RccIrConst): R {
        error("Not Implementation RccIrConst")
    }
}