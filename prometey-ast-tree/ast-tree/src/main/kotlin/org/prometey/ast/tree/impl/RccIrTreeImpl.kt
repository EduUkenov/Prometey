package org.prometey.ast.tree.impl

import org.prometey.ast.tree.RccIrElement
import org.prometey.ast.tree.RccIrTree

@PublishedApi
internal class RccIrTreeImpl(
    override val root: RccIrElement
) : RccIrTree