package org.prometey.description.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

class DescriptIrGenerationExtension : IrGenerationExtension {
    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {

        moduleFragment.transform(
            transformer = DescriptFunIntrinsicTransformer(pluginContext),
            data = null
        )
    }
}

//class DescriptorIntrinsicTransformer(
//    private val moduleFragment: IrModuleFragment,
//    private val pluginContext: IrPluginContext,
//) : IrElementTransformerVoid() {

//	private val pluginGeneratedDeclarationImplClass = pluginContext.referenceClass(
//		generatedDeclarationClassId
//	)!!.owner
//
//	val descriptor = pluginContext.referenceFunctions(EntityNames.descriptorIntrinsicCallableId).single()
//
//	@OptIn(DeprecatedForRemovalCompilerApi::class)
//	override fun visitCall(
//		expression: IrCall,
//	): IrExpression {
//		if (expression.symbol == descriptor) {
//			val value1 = expression.getValueArgument(0) as? IrFunctionReference
//
//			if (value1 != null) {
//				val newCall = IrConstructorCallImpl(
//					startOffset = -1,
//					endOffset = -1,
//					type = pluginGeneratedDeclarationImplClass.defaultType,
//					symbol = pluginGeneratedDeclarationImplClass.primaryConstructor!!.symbol,
//					typeArgumentsCount = 0,
//					constructorTypeArgumentsCount = 0
//				).apply {
//					putValueArgument(
//						0, IrConstImpl.string(
//							startOffset = -1,
//							endOffset = -1,
//							type = pluginContext.irBuiltIns.stringType,
//							value = value1.reflectionTarget?.owner?.kotlinFqName.toString()
//						)
//					)
//
//					putValueArgument(
//						1, IrConstImpl.int(
//							startOffset = -1,
//							endOffset = -1,
//							type = pluginContext.irBuiltIns.intType,
//							value = value1.reflectionTarget?.owner?.valueParameters?.count {
//								!it.name.asString().startsWith("$")
//							} ?: 0
//						)
//					)
//				}
//
//				return newCall
//			} else {
//				return super.visitCall(expression)
//			}
//
//		}
//
//		return super.visitCall(expression)
//	}
//}