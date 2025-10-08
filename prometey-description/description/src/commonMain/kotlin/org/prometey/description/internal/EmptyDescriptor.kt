package org.prometey.description.internal

import org.prometey.description.Descriptor
import org.prometey.description.DescriptKing

@PublishedApi
internal object EmptyDescriptor : Descriptor {
    override val king: DescriptKing get() = error("Нет")

    override fun equals(other: Any?): Boolean = other is EmptyDescriptor
    override fun hashCode(): Int = 0
    override fun toString(): String = "DescriptionEmpty"

    val name: String get() = TODO("Not yet implemented")
    val parameterCount: Int get() = TODO("Not yet implemented")

    fun getParameterDescript(index: Int): Descriptor {
        TODO("Not yet implemented")
    }

    val annotations: Set<Annotation> get() = TODO("Not yet implemented")
}