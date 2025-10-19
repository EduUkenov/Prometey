package org.prometey.ast.tree.annotation

/**
 * Applying this annotation generates a class that contains the abstract syntax tree (AST)
 * representation of the annotated entity.
 *
 * The generated class is placed in the same package as the original entity. Its name is derived
 * from the name of the annotated entity with the suffix "AstTree". If the [name] parameter is
 * specified, it takes precedence.
 *
 * - `name` — must be unique within a single package.
 *   Required if the target declaration supports overloading.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
public annotation class AstTree(
    val name: String = ""
)