package org.prometey.description.internal

import org.prometey.description.DescriptKing
import org.prometey.description.DescriptStructure
import org.prometey.description.Descriptor
import org.prometey.description.DescriptorFunction

@PublishedApi
internal class PluginGeneratedDescriptorFunction(
    override val fqName: String,
    override val parameterCount: Int,
//    override val returnDescriptor: Descriptor,
//    override val extensionDescriptor: Descriptor,
) : DescriptorFunction {
    override val king: DescriptKing get() = DescriptStructure.FUNCTION
    override val returnDescriptor: Descriptor
        get() = TODO("Not yet implemented")
    override val extensionDescriptor: Descriptor
        get() = TODO("Not yet implemented")

    override fun getParameterDescriptor(index: Int): Descriptor = EmptyDescriptor
}