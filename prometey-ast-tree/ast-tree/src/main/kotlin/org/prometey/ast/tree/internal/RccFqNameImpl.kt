package org.prometey.ast.tree.internal

import org.prometey.ast.tree.RccFqName
import org.prometey.ast.tree.RccName

@PublishedApi
internal class RccFqNameImpl(
    override val packageName: String,
    override val name: RccName
) : RccFqName