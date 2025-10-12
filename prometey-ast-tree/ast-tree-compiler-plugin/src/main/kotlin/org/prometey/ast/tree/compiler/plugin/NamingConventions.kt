package org.prometey.ast.tree.compiler.plugin

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.prometey.ast.tree.compiler.plugin.AstTreePackages.internalPackageName

internal object AstTreePackages {
    val internalPackageName = FqName("org.prometey.ast.tree.internal")
    val packageFqName = FqName("org.prometey.ast.tree")
    val packageFqAnnotationName = FqName("org.prometey.ast.tree.annotation")
}

object AstTreeAnnotations {
    val astTreeAnnotationFqName = FqName("org.prometey.ast.tree.annotation.Ast")
}

internal object EntityNames {
    val rccIrTree = ClassId(internalPackageName, Name.identifier("RccIrTree"))
    val rccFqNameClassId = ClassId(internalPackageName, Name.identifier("RccFqName"))
    val rccNameClassId = ClassId(internalPackageName, Name.identifier("RccName"))
    val rccIrFunctionImplClassId = ClassId(internalPackageName, Name.identifier("RccIrFunctionImpl"))
}