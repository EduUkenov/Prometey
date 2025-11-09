package org.prometey.sdui.metaFactory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.prometey.ast.Parameter
import org.prometey.ast.RccApplication

class MetaFactoryPassableRender(
    val application: RccApplication
) {
    fun metaFactory(): @Composable () -> Unit = {
        application.find<@Composable () -> Unit>("androidx.compose.foundation.layout.Column")?.let {
            Column(
                modifier = Modifier.Companion
                    .background(Color.Companion.Red)
                    .size(200.dp)
            ) {
                it.declarate.invoke(Parameter.Companion)
            }
        }
    }
}