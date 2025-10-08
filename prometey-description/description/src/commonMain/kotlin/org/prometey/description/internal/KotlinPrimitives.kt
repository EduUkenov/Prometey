package org.prometey.description.internal

import org.prometey.description.DescriptKing
import org.prometey.description.DescriptorType
import org.prometey.description.Primitives

abstract class DescriptorPrimitive(
    override val name: String,
    override val king: DescriptKing,
) : DescriptorType {
    override fun toString(): String = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DescriptorPrimitive) return false
        if (name == other.name && king == other.king) return true
        return false
    }

    override fun hashCode(): Int = name.hashCode() + 31 * king.hashCode()
}

object DescriptorUnit : DescriptorPrimitive("kotlin.Unit", Primitives.UNIT)

object DescriptorByte : DescriptorPrimitive("kotlin.Byte", Primitives.BYTE)

object DescriptorShort : DescriptorPrimitive("kotlin.Short", Primitives.SHORT)

object DescriptorInt : DescriptorPrimitive("kotlin.Int", Primitives.INT)

object DescriptorLong : DescriptorPrimitive("kotlin.Long", Primitives.LONG)

object DescriptorFloat : DescriptorPrimitive("kotlin.Float", Primitives.FLOAT)

object DescriptorDouble : DescriptorPrimitive("kotlin.Double", Primitives.DOUBLE)

object DescriptorString : DescriptorPrimitive("kotlin.String", Primitives.STRING)