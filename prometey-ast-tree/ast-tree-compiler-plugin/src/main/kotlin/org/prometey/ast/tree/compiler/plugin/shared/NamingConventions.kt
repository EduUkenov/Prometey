package org.prometey.ast.tree.compiler.plugin.shared

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.prometey.ast.tree.compiler.plugin.shared.AstTreePackages.astTreePackage
import org.prometey.ast.tree.compiler.plugin.shared.AstTreePackages.astTreePackageImpl

object AstTreePackages {
    val astTreePackage = FqName("org.prometey.ast.tree")
    val astTreePackageImpl = FqName("org.prometey.ast.tree")
    val astTreePackageDeclaration = FqName("org.prometey.ast.tree.declarations")
    val astTreePackageDeclarationImpl = FqName("org.prometey.ast.tree.declarations.impl")
    val astTreePackageExpression = FqName("org.prometey.ast.tree.expression")
    val astTreePackageExpressionImpl = FqName("org.prometey.ast.tree.expression.impl")
    val astTreePackageName = FqName("org.prometey.ast.tree.name")
    val astTreePackageNameImpl = FqName("org.prometey.ast.tree.name.impl")
}

object AstTreeAnnotations {
    val astTreeAnnotationFqName = FqName("org.prometey.ast.tree.annotation.AstTree")
    val astTreeAnnotationClassId = ClassId.topLevel(astTreeAnnotationFqName)

    object AstTreeParameterNames {
        val NAME = Name.identifier("name")
    }
}

object AstTreeEntityNames {
    const val AST_TREE = "AstTree"
}

object AstTreeClassIds {
    val rccElement = ClassId(astTreePackage, Name.identifier("RccIrElement"))
    val rccIrTree = ClassId(astTreePackage, Name.identifier("RccIrTree"))
    val rccIrTreeImpl = ClassId(astTreePackageImpl, Name.identifier("RccIrTreeImpl"))
}
