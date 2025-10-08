package org.prometey.sdui

import androidx.compose.runtime.Composable

class MetaFactoryComposableLambda(
    private val parameterCount: Int
) {
    @Suppress("UNCHECKED_CAST")
    @Composable
    fun <T> metaFactory(): T = when (parameterCount) {
        0 -> @Composable {
            composableP0()
        }

        else -> error("")
    } as T

    internal companion object {

    }
}

private fun composableP0(): @Composable () -> Unit = {
    PassableRender()
}