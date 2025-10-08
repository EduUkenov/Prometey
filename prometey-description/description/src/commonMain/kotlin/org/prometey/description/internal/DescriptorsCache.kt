package org.prometey.description.internal

import org.prometey.description.DescriptorClass

internal object DescriptorsCache {
    val cache: MutableMap<String, DescriptorClass> = hashMapOf()
}