package org.prometey.ast.tree.name.impl

import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.name.RccName

@Deprecated("")
@PublishedApi
internal class RccFqNameImpl(
    override val packageName: String,
    override val name: RccName
) : RccFqName