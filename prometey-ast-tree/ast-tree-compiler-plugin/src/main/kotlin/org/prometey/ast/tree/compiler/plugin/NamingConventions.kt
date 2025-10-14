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

}

internal object AstTreeClassIds {
    val rccIrElement = ClassId(packageAstTreeName, Name.identifier("RccIrElement"))

    val rccIrName = ClassId(packageAstTreeName, Name.identifier("RccIrName"))
    val rccIrFqName =  ClassId(packageAstTreeName, Name.identifier("RccIrName"))

    val rccIrTree = ClassId(packageAstTreeName, Name.identifier("RccIrTree"))
    val rccIrBody =  ClassId(packageAstTreeName, Name.identifier("RccIrBody"))
    val rccIrFunction = ClassId(packageAstTreeName, Name.identifier("RccIrFunction"))
    val rccIrLambda = ClassId(packageAstTreeName, Name.identifier("RccIrLambda"))
    val rccIrType = ClassId(packageAstTreeName, Name.identifier("RccIrLambda"))

    // implementation zone

    val rccIrNameImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrNameImpl"))
    val rccIrFqNameImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrFqNameImpl"))

    val rccIrTreeImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrTreeImpl"))
    val rccIrBodyImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrBodyImpl"))
    val rccIrFunctionImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrFunctionImpl"))
    val rccIrLambdaImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrLambdaImpl"))
    val rccIrTypeImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrTypeImpl"))
}