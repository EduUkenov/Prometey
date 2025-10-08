package org.prometey.ast

import org.prometey.description.DescriptorFunction

interface RccApplication {

    fun <T> find(
        fqName: String,
    ): Declaration<T>?
}

fun rcc(
    init: Builder.() -> Unit
): RccApplication = RccApplicationImpl().apply(init)

class RccApplicationImpl() : RccApplication, Builder {
    val declarations = mutableMapOf<String, Declaration<*>>()

    override fun <T> bind(declaration: Declaration<T>) {
        when {
            declaration.descriptor is DescriptorFunction -> {
                declarations[declaration.descriptor.fqName] = declaration
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> find(fqName: String): Declaration<T>? = declarations[fqName] as Declaration<T>
}