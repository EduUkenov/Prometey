package org.prometey.sdui.metaFactory

import androidx.compose.runtime.Composable

/*
 * Todo необходимо реализовать до 22, затем сделать поддержку FunctionN.
 */
@Suppress("UNCHECKED_CAST")
internal fun <T> metaFactoryComposableLambda(parameterCount: Int): T = when (parameterCount) {
    0 -> composableP0()
    1 -> composableP1()
    else -> error("")
} as T

internal fun composableP0(): @Composable () -> Any = {

}

internal fun composableP1(): @Composable (Any?) -> Any = {

}