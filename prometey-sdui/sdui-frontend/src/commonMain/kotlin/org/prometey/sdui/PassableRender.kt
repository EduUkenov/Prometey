package org.prometey.sdui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

interface PassableRenderState {
    fun render(): @Composable () -> Unit
}

class PassableRenderStateImpl() : PassableRenderState {


    override fun render(): @Composable (() -> Unit) {
        return TODO()
    }
}

@Composable
fun rememberPassableRenderState(): PassableRenderState = remember { PassableRenderStateImpl() }

@Composable
fun PassableRender(
    state: PassableRenderState = rememberPassableRenderState()
) {
    state.render().run {
        this()
    }
}