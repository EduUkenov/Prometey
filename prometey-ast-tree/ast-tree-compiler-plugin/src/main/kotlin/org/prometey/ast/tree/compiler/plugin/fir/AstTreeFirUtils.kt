package org.prometey.ast.tree.compiler.plugin.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.getAnnotationByClassId
import org.jetbrains.kotlin.fir.declarations.getStringArgument
import org.jetbrains.kotlin.fir.expressions.FirAnnotation
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeAnnotations.AstTreeParameterNames
import org.prometey.ast.tree.compiler.plugin.shared.AstTreeAnnotations.astTreeAnnotationClassId

fun FirBasedSymbol<*>.getAstTreeAnnotation(session: FirSession): FirAnnotation? =
    resolvedAnnotationsWithArguments.getAnnotationByClassId(astTreeAnnotationClassId, session)

fun FirBasedSymbol<*>.getAstTreeNameValue(session: FirSession): String? =
    getAstTreeAnnotation(session)?.getStringArgument(AstTreeParameterNames.NAME, session)