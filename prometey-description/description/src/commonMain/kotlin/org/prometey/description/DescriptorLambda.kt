package org.prometey.description

interface DescriptorLambda : Descriptor {
	override val king: DescriptKing get() = DescriptStructure.LAMBDA
	
	val returnDescriptor: Descriptor
	
	val parameterCount: Int
	
	fun getParameterDescript(index: Int): Descriptor
	
	val receiverDescriptor: Descriptor
	
	val isNullable: Boolean
	
	val annotations: Set<Annotation> get() = setOf()
}
