package org.prometey.description

@JvmInline
value class DescriptKingInt @PublishedApi internal constructor(val value: Int) {
    companion object {
        val FUNCTION = DescriptKingInt(3)
    }
}