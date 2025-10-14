package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.prometey.ast.tree.compiler.plugin.AstTreeClassIds

class AstTreeContext(
    astTreeContext: IrPluginContext
) : IrPluginContext by astTreeContext {
    val rccIrElement = referenceClass(AstTreeClassIds.rccIrElement)
        ?: error("Not found: ${AstTreeClassIds.rccIrElement}")

    val rccIrNameImpl = referenceClass(AstTreeClassIds.rccIrNameImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrNameImpl}")

    val rccIrFqNameImpl = referenceClass(AstTreeClassIds.rccIrFqNameImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrFqNameImpl}")

    val rccIrTreeImpl = referenceClass(AstTreeClassIds.rccIrTreeImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrTreeImpl}")

    val rccIrBodyImpl = referenceClass(AstTreeClassIds.rccIrBodyImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrBodyImpl}")

    val rccIrFunctionImpl = referenceClass(AstTreeClassIds.rccIrFunctionImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrFunctionImpl}")

    val rccIrLambdaImpl = referenceClass(AstTreeClassIds.rccIrLambdaImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrLambdaImpl}")

    val rccIrTypeImpl =  referenceClass(AstTreeClassIds.rccIrTypeImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrTypeImpl}")
}