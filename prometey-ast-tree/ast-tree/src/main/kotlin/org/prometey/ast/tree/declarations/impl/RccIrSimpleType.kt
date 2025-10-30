package org.prometey.ast.tree.declarations.impl

import org.prometey.ast.tree.declarations.RccIrTypeSimple
import org.prometey.ast.tree.name.RccFqName

@PublishedApi
internal class RccIrTypeSimpleImpl(
    override val fqName: RccFqName
) : RccIrTypeSimple