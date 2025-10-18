package org.prometey.ast.tree.name.impl

import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.name.RccName

@PublishedApi
internal class RccFqNameImpl(
    override val packageName: String,
    override val name: RccName
) : RccFqName