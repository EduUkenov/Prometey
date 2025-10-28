package org.prometey.ast.tree.compiler.plugin.shared

import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId

sealed class Identifier() {
    class Callable(val callableId: CallableId) : Identifier()
    class Clazz(val classId: ClassId) : Identifier()
}