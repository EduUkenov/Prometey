package org.prometey.description

interface DescriptorFunction : Descriptor {
    val fqName: String

    val parameterCount: Int

    val returnDescriptor: Descriptor

    val extensionDescriptor: Descriptor

    fun getParameterDescriptor(index: Int): Descriptor
}

