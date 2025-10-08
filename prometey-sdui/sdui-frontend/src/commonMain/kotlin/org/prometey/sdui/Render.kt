package org.prometey.sdui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.prometey.ast.RccApplication

/*
 * Сам по себе Render не должен знать об url или же об path, будет более естественно если это определяется
 * в Rcc, а Render будет знать откуда что-то доставать.
 *
 * Render должен знать кто он допустим MainScreen, но не должен знать чем он является.
 */

class RenderStateImpl<T>(
    override val name: T,
) : RenderState<T> {

}

interface RenderState<T> {
    val name: T
}

@Composable
fun <T> rememberRenderState(
    name: T
) = remember(name) { RenderStateImpl(name = name) }

@Composable
fun <T> Render(
    state: RenderState<T>,
    rccApplication: RccApplication
) {

}