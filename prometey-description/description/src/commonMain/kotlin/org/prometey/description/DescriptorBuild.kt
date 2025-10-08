package org.prometey.description

import org.prometey.description.annotation.Descriptor

@Descriptor
fun <T> descriptorClass(described: T): DescriptorClass = throw NotImplementedError(MESSAGE_ERROR)

@Descriptor
fun <T> descriptorLambda(): DescriptorLambda = throw NotImplementedError(MESSAGE_ERROR)

@Descriptor
fun <T> descriptorFun(described: T): DescriptorFunction = throw NotImplementedError(MESSAGE_ERROR)

const val MESSAGE_ERROR = "This is intrinsic, must be replaced by compiler"



