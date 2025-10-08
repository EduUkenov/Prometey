package org.prometey.description

interface DescriptorClass : Descriptor {

    val name: String

    fun getConstructorDescriptor(index: Int): DescriptorFunction
}

