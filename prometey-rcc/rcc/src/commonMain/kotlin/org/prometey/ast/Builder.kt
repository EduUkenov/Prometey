package org.prometey.ast

interface Builder {

    fun <T> bind(
        declaration: Declaration<T>
    )
}

