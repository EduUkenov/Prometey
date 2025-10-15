package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.prometey.ast.tree.compiler.plugin.AstTreeClassIds

class AstTreeContext(
    astTreeContext: IrPluginContext
) : IrPluginContext by astTreeContext {
    val rccIrElement = referenceClass(AstTreeClassIds.rccIrElement)
        ?: error("Not found: ${AstTreeClassIds.rccIrElement}")

    val rccNameImpl = referenceClass(AstTreeClassIds.rccNameImpl)
        ?: error("Not found: ${AstTreeClassIds.rccNameImpl}")

    val rccFqNameImpl = referenceClass(AstTreeClassIds.rccFqNameImpl)
        ?: error("Not found: ${AstTreeClassIds.rccFqNameImpl}")

    val rccIrTreeImpl = referenceClass(AstTreeClassIds.rccIrTreeImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrTreeImpl}")

    val rccIrBodyImpl = referenceClass(AstTreeClassIds.rccIrBodyImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrBodyImpl}")

    val rccIrFunctionImpl = referenceClass(AstTreeClassIds.rccIrFunctionImpl)
        ?: error("Not found: ${AstTreeClassIds.rccIrFunctionImpl}")

//    val rccIrLambdaImpl = referenceClass(AstTreeClassIds.rccIrLambdaImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccIrLambdaImpl}")

//    val rccIrTypeImpl = referenceClass(AstTreeClassIds.rccIrTypeImpl)
//        ?: error("Not found: ${AstTreeClassIds.rccIrTypeImpl}")
}