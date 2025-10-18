package org.prometey.ast.tree.annotation

/**
 * By applying this annotation, you will get a generated class containing the abstract syntax tree (AST) of the annotated entity.
 * The generated class will be placed in the same package as the entity itself.
 *
 * @param name must be unique within a single package. It is required if the target supports overloading.
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
public annotation class AstTree(
    val name: String = ""
)