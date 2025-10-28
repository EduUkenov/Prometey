package org.prometey.ast.tree.compiler.plugin.shared

import org.jetbrains.kotlin.GeneratedDeclarationKey

object AstTreeGeneratedKey : GeneratedDeclarationKey() {
    override fun toString(): String {
        return "AstTreePlugin"
    }
}

data class AstTreeGeneratedClazzKey(
    val source: Identifier,
    val generated: Identifier.Clazz,
) : GeneratedDeclarationKey()