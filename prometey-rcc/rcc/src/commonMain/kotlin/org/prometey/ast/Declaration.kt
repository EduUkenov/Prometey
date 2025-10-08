package org.prometey.ast

import kotlinx.serialization.Serializable
import org.prometey.description.Descriptor

data class Declaration<out T>(
    val descriptor: Descriptor,
    val declarate: (Parameter) -> T
)

fun <T> Builder.declaration(
    descriptor: Descriptor,
    declarate: (Parameter) -> T
) {
    bind(
        Declaration(
            descriptor = descriptor,
            declarate = declarate
        )
    )
}

@Serializable
class Edu

fun main() {
    Edu.serializer()
}