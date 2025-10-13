package org.prometey.ast.tree.compiler.plugin

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.prometey.ast.tree.compiler.plugin.AstTreePackages.internalPackageAstTreeName
import org.prometey.ast.tree.compiler.plugin.AstTreePackages.packageAstTreeName

internal object AstTreePackages {
    val internalPackageAstTreeName = FqName("org.prometey.ast.tree.internal")
    val packageAstTreeName = FqName("org.prometey.ast.tree")
    val packageFqName = FqName("org.prometey.ast.tree")
    val packageFqAnnotationName = FqName("org.prometey.ast.tree.annotation")
}

object AstTreeAnnotations {
    val astTreeAnnotationFqName = FqName("org.prometey.ast.tree.annotation.Ast")
}

internal object EntityNames {
    val rccIrTree = ClassId(packageAstTreeName, Name.identifier("RccIrTree"))
    val rccIrTreeImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrTreeImpl"))

    val rccFqNameClassId = ClassId(internalPackageAstTreeName, Name.identifier("RccFqName"))
    val rccNameClassId = ClassId(internalPackageAstTreeName, Name.identifier("RccName"))
    val rccIrFunctionImplClassId = ClassId(internalPackageAstTreeName, Name.identifier("RccIrFunctionImpl"))
}