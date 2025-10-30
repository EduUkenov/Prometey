package org.prometey.ast.tree.compiler.plugin.shared

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.prometey.ast.tree.compiler.plugin.shared.AstTreePackages.astTreePackage
import org.prometey.ast.tree.compiler.plugin.shared.AstTreePackages.astTreePackageDeclaration
import org.prometey.ast.tree.compiler.plugin.shared.AstTreePackages.astTreePackageDeclarationImpl
import org.prometey.ast.tree.compiler.plugin.shared.AstTreePackages.astTreePackageImpl
import org.prometey.ast.tree.compiler.plugin.shared.AstTreePackages.astTreePackageName
import org.prometey.ast.tree.compiler.plugin.shared.AstTreePackages.astTreePackageNameImpl

object AstTreePackages {
    val astTreePackage = FqName("org.prometey.ast.tree")
    val astTreePackageImpl = FqName("org.prometey.ast.tree.impl")
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
    val rccName = ClassId(astTreePackageName, Name.identifier("RccIrName"))
    val rccNameImpl = ClassId(astTreePackageNameImpl, Name.identifier("RccIrNameImpl"))
    val rccFqName = ClassId(astTreePackageName, Name.identifier("RccIrFqName"))
    val rccFqNameImpl = ClassId(astTreePackageNameImpl, Name.identifier("RccIrFqNameImpl"))

    val rccElement = ClassId(astTreePackage, Name.identifier("RccIrElement"))

    val rccIrTree = ClassId(astTreePackage, Name.identifier("RccIrTree"))
    val rccIrTreeImpl = ClassId(astTreePackageImpl, Name.identifier("RccIrTreeImpl"))

    val rccIrType = ClassId(astTreePackageDeclaration, Name.identifier("RccIrType"))
    val rccIrTypeSimple = ClassId(astTreePackageDeclaration, Name.identifier("RccIrTypeSimple"))
    val rccIrTypeSimpleImpl = ClassId(astTreePackageDeclarationImpl, Name.identifier("RccIrTypeSimpleImpl"))

    val rccIrFunction = ClassId(astTreePackageDeclaration, Name.identifier("RccIrFunction"))
    val rccIrFunctionImpl = ClassId(astTreePackageDeclarationImpl, Name.identifier("RccIrFunctionImpl"))
}
