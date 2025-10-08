package org.prometey.sdui

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("ModifierFactoryExtensionFunction")
class MetaFactoryModifier() {
    fun metaFactory(): Modifier {
        return Modifier then Modifier.size(1.dp)
    }

    internal companion object {

    }
}