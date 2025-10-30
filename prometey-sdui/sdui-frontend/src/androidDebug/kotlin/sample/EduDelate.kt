package sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.prometey.sdui.ModifierList
import org.prometey.sdui.modifierListOfImpl

@Preview
@Composable
fun Edu() {
    val modifier: ModifierList = modifierListOfImpl(
        Modifier.size(200.dp),
        Modifier.height(100.dp),
        Modifier.width(200.dp),
        Modifier.background(Color.Blue),
    )

    Column {
        Box(
            modifier = modifier
        )
    }
}