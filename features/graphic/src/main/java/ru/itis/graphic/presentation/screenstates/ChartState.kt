package ru.itis.graphic.presentation.screenstates

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class GraphState(
    val pointsCountInput: String = "",
    val valuesInput: String = "",
    val points: ImmutableList<Float> = persistentListOf(),
    val error: String? = null
)