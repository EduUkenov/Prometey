package org.prometey.description.internal

import org.prometey.description.DescriptKing
import org.prometey.description.DescriptStructure
import org.prometey.description.DescriptorClass
import org.prometey.description.DescriptorFunction

@PublishedApi
internal class PluginGeneratedDescriptorClass(
    override val name: String,
) : DescriptorClass {
    override val king: DescriptKing get() = DescriptStructure.CLASS

    override fun getConstructorDescriptor(index: Int): DescriptorFunction {
        TODO("Not yet implemented")
    }
}