package org.prometey.ast.tree.name.impl

import org.prometey.ast.tree.name.RccClassId
import org.prometey.ast.tree.name.RccQualifiedName

@PublishedApi
internal class RccClassIdImpl(
    override val qualifiedName: RccQualifiedName
) : RccClassId