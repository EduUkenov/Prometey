package org.prometey.ast.tree.name

interface RccCallableId {
    val classId: RccClassId?
    val qualifiedName: RccQualifiedName
}