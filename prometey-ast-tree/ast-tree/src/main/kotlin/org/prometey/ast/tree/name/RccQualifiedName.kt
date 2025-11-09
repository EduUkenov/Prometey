package org.prometey.ast.tree.name

interface RccQualifiedName {
    val packageName: RccPackageName
    val name: RccName
}