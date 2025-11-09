package org.prometey.ast.tree.name.impl

import org.prometey.ast.tree.name.RccCallableId
import org.prometey.ast.tree.name.RccClassId
import org.prometey.ast.tree.name.RccQualifiedName

@PublishedApi
internal class RccCallableIdImpl(
    override val classId: RccClassId,
    override val qualifiedName: RccQualifiedName
) : RccCallableId