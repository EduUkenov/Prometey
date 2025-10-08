package org.prometey.description.compiler.plugin

import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.prometey.description.compiler.plugin.DescriptorPackages.internalPackageFqName
import org.prometey.description.compiler.plugin.DescriptorPackages.packageFqName

internal object DescriptorPackages {
    val packageFqName = FqName("org.prometey.description")
    val internalPackageFqName = FqName("org.prometey.description.internal")
}

internal object EntityNames {
    private const val PLUGIN_GENERATED_DESCRIPT_CLASS = "PluginGeneratedDescriptorClass"
    private const val PLUGIN_GENERATED_DESCRIPT_FUNCTION = "PluginGeneratedDescriptorFunction"
    private const val PLUGIN_GENERATED_DESCRIPT_LAMBDA = "PluginGeneratedDescriptorLambda"

    private const val DESCRIPT = "Descriptor"
    private const val EMPTY_DESCRIPT = "EmptyDescriptor"
    private const val DESCRIPTOR_CLASS_INTRINSIC = "descriptorClass"
    private const val DESCRIPTOR_FUN_INTRINSIC = "descriptorFun"
    private const val DESCRIPTOR_LAMBDA_INTRINSIC = "descriptorLambda"

    val descriptor = ClassId(packageFqName, Name.identifier(DESCRIPT))
    val emptyDescriptor = ClassId(internalPackageFqName, Name.identifier(EMPTY_DESCRIPT))
    val pluginGeneratedDescriptorClass = ClassId(internalPackageFqName, Name.identifier(PLUGIN_GENERATED_DESCRIPT_CLASS))
    val pluginGeneratedDescriptorFunction = ClassId(internalPackageFqName, Name.identifier(PLUGIN_GENERATED_DESCRIPT_FUNCTION))
    val pluginGeneratedDescriptorLambda = ClassId(internalPackageFqName, Name.identifier(PLUGIN_GENERATED_DESCRIPT_LAMBDA))

    val descriptorClassIntrinsic = CallableId(packageFqName, Name.identifier(DESCRIPTOR_CLASS_INTRINSIC))
    val descriptorFunIntrinsic = CallableId(packageFqName, Name.identifier(DESCRIPTOR_FUN_INTRINSIC))
    val descriptorLambdaIntrinsic = CallableId(packageFqName, Name.identifier(DESCRIPTOR_LAMBDA_INTRINSIC))
}

internal object PrimitiveBuiltins {
    private const val DESCRIPTOR_UNIT = "DescriptorUnit"
    private const val DESCRIPTOR_BYTE = "DescriptorByte"
    private const val DESCRIPTOR_SHORT = "DescriptorShort"
    private const val DESCRIPTOR_INT = "DescriptorInt"
    private const val DESCRIPTOR_LONG = "DescriptorLong"
    private const val DESCRIPTOR_FLOAT = "DescriptorFloat"
    private const val DESCRIPTOR_DOUBLE = "DescriptorDouble"
    private const val DESCRIPTOR_STRING = "DescriptorDouble"

    val descriptorUnit = ClassId(internalPackageFqName, Name.identifier(DESCRIPTOR_UNIT))
    val descriptorByte = ClassId(internalPackageFqName, Name.identifier(DESCRIPTOR_BYTE))
    val descriptorShort = ClassId(internalPackageFqName, Name.identifier(DESCRIPTOR_SHORT))
    val descriptorInt = ClassId(internalPackageFqName, Name.identifier(DESCRIPTOR_INT))
    val descriptorLong = ClassId(internalPackageFqName, Name.identifier(DESCRIPTOR_LONG))
    val descriptorFloat = ClassId(internalPackageFqName, Name.identifier(DESCRIPTOR_FLOAT))
    val descriptorDouble = ClassId(internalPackageFqName, Name.identifier(DESCRIPTOR_DOUBLE))
    val descriptorString = ClassId(internalPackageFqName, Name.identifier(DESCRIPTOR_STRING))
}