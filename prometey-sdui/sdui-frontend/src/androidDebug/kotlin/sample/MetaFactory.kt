package sample

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.prometey.sdui.metaFactory.metaFactoryComposableLambda

@Preview
@Composable
fun Render_Sample() {
    Scaffold(
        topBar = metaFactoryComposableLambda(0),
        content = metaFactoryComposableLambda(1)
    )
}