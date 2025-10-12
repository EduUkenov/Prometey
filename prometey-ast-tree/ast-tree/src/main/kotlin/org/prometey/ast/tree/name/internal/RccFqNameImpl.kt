package org.prometey.ast.tree.name.internal

import org.prometey.ast.tree.name.RccFqName
import org.prometey.ast.tree.name.RccName

class RccFqNameImpl(
    override val packageName: String,
    override val name: RccName
) : RccFqName