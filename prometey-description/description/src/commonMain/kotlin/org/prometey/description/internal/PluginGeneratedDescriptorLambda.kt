package org.prometey.description.internal

import org.prometey.description.Descriptor
import org.prometey.description.DescriptorLambda

@PublishedApi
internal class PluginGeneratedDescriptorLambda(
    override val returnDescriptor: Descriptor,
    override val parameterCount: Int,
    override val receiverDescriptor: Descriptor,
    override val isNullable: Boolean,
    private val generatedDescriptor: Array<out Descriptor>,
) : DescriptorLambda {
    override fun getParameterDescript(index: Int): Descriptor {
        TODO("Not yet implemented")
    }
}