package org.prometey.ast.tree.internal

import org.prometey.ast.tree.RccFqName
import org.prometey.ast.tree.RccName

internal class RccFqNameImpl(
    override val packageName: String,
    //override val name: RccName
) : RccFqName {
    override val name: RccName
        get() = TODO("Not yet implemented")
}