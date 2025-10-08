package org.prometey.ast

interface Parameter {
    fun <T> get(): T

    companion object : Parameter {
        override fun <T> get(): Nothing = throw IllegalStateException("")
    }
}