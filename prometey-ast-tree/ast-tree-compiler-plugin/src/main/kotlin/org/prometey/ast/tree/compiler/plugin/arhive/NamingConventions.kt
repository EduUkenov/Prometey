package org.prometey.ast.tree.compiler.plugin.arhive

import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.prometey.ast.tree.compiler.plugin.arhive.AstTreePackages.internalPackageAstTreeName
import org.prometey.ast.tree.compiler.plugin.arhive.AstTreePackages.packageAstTreeName
import org.prometey.ast.tree.compiler.plugin.arhive.AstTreePackages.packageFqAnnotationName
import org.prometey.ast.tree.compiler.plugin.arhive.KotlinStdCollection.packageCollectionsFqName

object AstTreePackages {
    val internalPackageAstTreeName = FqName("org.prometey.ast.tree.internal")
    val packageAstTreeName = FqName("org.prometey.ast.tree")
    val packageFqName = FqName("org.prometey.ast.tree")
    val packageFqAnnotationName = FqName("org.prometey.ast.tree.annotation")
}

internal object KotlinStdCollection {
    val packageCollectionsFqName = FqName("kotlin.collections")
}

object AstTreeAnnotations {
    val astTreeAnnotationFqName = FqName("org.prometey.ast.tree.annotation.Ast")
}

internal object AstTreeClassIds {
    val rccIrElement = ClassId(packageAstTreeName, Name.identifier("RccIrElement"))

    val rccIrName = ClassId(packageAstTreeName, Name.identifier("RccName"))
    val rccIrFqName = ClassId(packageAstTreeName, Name.identifier("RccFqName"))

    val rccIrTree = ClassId(packageAstTreeName, Name.identifier("RccIrTree"))
    val rccIrBody = ClassId(packageAstTreeName, Name.identifier("RccIrBody"))
    val rccIrBodyBlock = ClassId(packageAstTreeName, Name.identifier("RccIrBodyBlock"))
    val rccIrFunction = ClassId(packageAstTreeName, Name.identifier("RccIrFunction"))
    val rccIrLambda = ClassId(packageAstTreeName, Name.identifier("RccIrLambda"))
    val rccIrType = ClassId(packageAstTreeName, Name.identifier("RccIrLambda"))

    // implementation zone

    val rccNameImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccNameImpl"))
    val rccFqNameImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccFqNameImpl"))

    val rccIrTreeImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrTreeImpl"))
    val rccIrBodyImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrBodyBlockImpl"))
    val rccIrFunctionImpl =
        ClassId(internalPackageAstTreeName, Name.identifier("RccIrFunctionImpl"))
    val rccIrLambdaImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrLambdaImpl"))
    val rccIrTypeImpl = ClassId(internalPackageAstTreeName, Name.identifier("RccIrTypeImpl"))
}

internal object KotlinCollectionCallableId {
    val listOf =
        CallableId(packageName = packageCollectionsFqName, callableName = Name.identifier("listOf"))
}