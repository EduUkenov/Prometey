package org.prometey.description

@JvmInline
value class DescriptorFunctionInt @PublishedApi internal constructor(
    @PublishedApi internal val structure: IntArray
) {
    inline val king: DescriptKingInt get() = DescriptKingInt(structure[DESCRIPTOR_INT])

    inline val identifier: Int get() = structure[IDENTIFIER]

    inline val count: Int get() = structure[COUNTER_PARAMETER]
}

@PublishedApi
internal const val DESCRIPTOR_INT = 0
@PublishedApi
internal const val IDENTIFIER = 1
@PublishedApi
internal const val COUNTER_PARAMETER = 1

