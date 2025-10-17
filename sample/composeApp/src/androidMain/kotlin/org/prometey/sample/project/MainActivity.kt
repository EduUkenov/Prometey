package org.prometey.sample.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import org.prometey.ast.RccApplication
import org.prometey.ast.declaration
import org.prometey.ast.rcc
import org.prometey.description.descriptorFun


class MainActivity : ComponentActivity() {

    lateinit var rcc: RccApplication
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        rcc = rcc {
            declaration(
                descriptor = descriptorFun<(Dp) -> Modifier>(Modifier::width)
            ) { parameter ->
                Modifier.width(parameter.get<Dp>())
            }

            declaration(
                descriptor = descriptorFun<(Dp) -> Modifier>(Modifier::height)
            ) { parameter ->
                Modifier.height(parameter.get<Dp>())
            }

            declaration(
                descriptor = descriptorFun<(Dp) -> Modifier>(Modifier::size)
            ) { parameter ->
                Modifier.size(parameter.get<Dp>())
            }

            declaration(
                descriptor = descriptorFun<(Color, Shape) -> Modifier>(Modifier::background)
            ) { parameter ->
                Modifier.background(parameter.get<Color>())
            }

            declaration(
                descriptor = descriptorFun(::Column)
            ) {
                @Composable {
                    Column(
                        modifier = it.get(),
                        verticalArrangement = it.get(),
                        horizontalAlignment = it.get(),
                        content = it.get()
                    )
                }
            }

            declaration(
                descriptor = descriptorFun(::LazyColumn)
            ) {
                @Composable {
                    LazyColumn(
                        modifier = it.get(),
                        content = it.get()
                    )
                }
            }
        }

        setContent {

        }
    }
}