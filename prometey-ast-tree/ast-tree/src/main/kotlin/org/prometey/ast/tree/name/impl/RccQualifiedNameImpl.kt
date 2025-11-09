package org.prometey.ast.tree.name.impl

import org.prometey.ast.tree.name.RccPackageName
import org.prometey.ast.tree.name.RccName
import org.prometey.ast.tree.name.RccQualifiedName

@PublishedApi
internal class RccQualifiedNameImpl(
    override val packageName: RccPackageName,
    override val name: RccName
) : RccQualifiedName