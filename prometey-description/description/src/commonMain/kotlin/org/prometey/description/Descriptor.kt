package org.prometey.description

import org.prometey.description.internal.EmptyDescriptor

interface Descriptor {
    val king: DescriptKing
}

fun Descriptor.isEmpty() = this is EmptyDescriptor

fun Descriptor.isNotEmpty() = !isEmpty()