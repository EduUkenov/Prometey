package org.prometey.sample.project

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsBytes
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.readRawBytes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.prometey.ast.RccApplication
import org.prometey.ast.declaration
import org.prometey.ast.rcc
import org.prometey.description.descriptorFun

val desciptor = descriptorFun(::Column)

class MainActivity : ComponentActivity() {
    val client = HttpClient(OkHttp)

    val coroutine = CoroutineScope(Dispatchers.IO)
    lateinit var rcc: RccApplication
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        coroutine.launch {
            val customer = client.get("http://localhost:8080/customer/3").bodyAsBytes()
        }

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
            Text(
                text = desciptor.fqName
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        client.close()
    }
}