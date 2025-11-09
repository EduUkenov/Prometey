package org.prometey.sdui.value

sealed class Value {

    class Boolean(val value: kotlin.Boolean) : Value()

    class Byte(val value: kotlin.Byte) : Value()

    class Short(val value: kotlin.Short) : Value()

    class Int(val value: kotlin.Int) : Value()

    class Long(val value: kotlin.Long) : Value()

    class Float(val value: kotlin.Float): Value()
}