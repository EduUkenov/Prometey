package foo.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.prometey.ast.tree.annotation.AstTree

@AstTree
@Composable
fun MainComponent() {
    Column(
        modifier = Modifier.size(1.dp)
    ) {
        Box() {

        }
    }
}