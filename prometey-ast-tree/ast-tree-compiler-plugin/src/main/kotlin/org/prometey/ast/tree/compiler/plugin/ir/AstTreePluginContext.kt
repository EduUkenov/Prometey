package org.prometey.ast.tree.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.util.isVararg
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeCallableIds
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeClassIds

class AstTreePluginContext(
    pluginContext: IrPluginContext
) : IrPluginContext by pluginContext {

    val listOfRef = pluginContext.referenceFunctions(AstTreeCallableIds.listOfCallableId).first {
        it.owner.parameters.size == 1 && it.owner.parameters.first().isVararg
    }

    val rccIrNameImplRef = pluginContext.referenceClass(AstTreeClassIds.rccNameImpl)!!
    val rccIrFqNameImplRef = pluginContext.referenceClass(AstTreeClassIds.rccFqNameImpl)!!
    val rccIrElementRef = pluginContext.referenceClass(AstTreeClassIds.rccElement)!!
    val rccIrTypeSimpleImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrTypeSimpleImpl)!!
    val rccIrFunctionImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrFunctionImpl)!!
    val rccIrBodyImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrBodyImpl)!!
    val rccIrCallImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrCallImpl)!!
    val rccIrFunctionExpressionRef = pluginContext.referenceClass(AstTreeClassIds.rccIrFunctionExpressionImpl)!!
    val rccIrReturnImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrReturnImpl)!!
    val rccIrGetObjectValueImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrGetObjectValueImpl)!!
    val rccIrConstImplRef = pluginContext.referenceClass(AstTreeClassIds.rccIrConstImpl)!!

}