package org.prometey.sdui

import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlin.experimental.ExperimentalTypeInference

/**
 * [ModifierList] это представление Modifier в виде [ImmutableList], что способствует удобной, работы с API
 * Collection.
 *
 * Скажем что Modifier это сырая структура, а это структура представленная [CombinedModifier], то
 * мы имеем единственный API [Modifier.then].
 *
 * Все extension fun Modifier реализуют явно или неявно [Modifier.then], а он образует [CombinedModifier].
 *
 * Box(
 *     modifier = Modifier
 * 		    .clip(RoundedCornerShape(22.dp))
 * 			.background(Color.Black)
 * 			.size(200.dp)
 * )
 *
 */
class ModifierList @PublishedApi internal constructor(
    private val modifies: ImmutableList<Modifier>,
) : Modifier, ImmutableList<Modifier> by modifies {

    /**
     * Причина по которой мы используем CombinedModifier, а не адаптируем функции интерфейса [Modifier]
     * под работу с [modifies] кроится, в том что мы не можем знать как будет развиваться CombinedModifier
     * командой Jetpack Compose.
     *
     * - Проблема заключается что свойства `CombinedModifier` являются internal а не private.
     *
     * - В целом API Modifier строиться на неявном приведении типов, так например
     * в [androidx.compose.ui.materialize] будет неявно использована реализация [CombinedModifier],
     * если кол-во цепочек Modifier больше двух.
     */
    private val combinedModifier: CombinedModifier

    init {
        val result = modifies.fold(ModifierEmpty) { acc, modifier ->
            CombinedModifier(acc, modifier)
        }

        combinedModifier = result
    }

    override fun all(predicate: (Modifier.Element) -> Boolean): Boolean =
        combinedModifier.all(predicate)

    override fun any(predicate: (Modifier.Element) -> Boolean): Boolean =
        combinedModifier.any(predicate)

    override fun <R> foldIn(initial: R, operation: (R, Modifier.Element) -> R): R =
        combinedModifier.foldIn(initial, operation)

    override fun <R> foldOut(initial: R, operation: (Modifier.Element, R) -> R): R =
        combinedModifier.foldOut(initial, operation)

    override fun equals(other: Any?): Boolean = other === modifies

    override fun hashCode(): Int = modifies.hashCode()

    companion object {
        private val ModifierEmpty = CombinedModifier(Modifier, Modifier)
    }
}

@OptIn(ExperimentalTypeInference::class)
inline fun modifierBuildList(
    @BuilderInference
    builderAction: MutableList<Modifier>.() -> Unit,
): ModifierList {
    val result = buildList(builderAction).toImmutableList()

    return ModifierList(result)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun modifierListOfImpl(vararg modifiers: Modifier): ModifierList {
    val result = buildList {
        for (modifier in modifiers) {
            add(modifier)
        }
    }.toImmutableList()

    return ModifierList(result)
}

