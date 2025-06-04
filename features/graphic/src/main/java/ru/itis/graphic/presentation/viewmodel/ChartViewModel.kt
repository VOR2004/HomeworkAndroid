package ru.itis.graphic.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import ru.itis.presentation.ui.viewmodel.BaseViewModel
import ru.itis.graphic.presentation.screenstates.GraphState
import ru.itis.graphic.utils.ErrorMessages

class GraphViewModel (
) : BaseViewModel<GraphState>(GraphState()) {

    fun onCountChanged(newValue: String) {
        viewState = viewState.copy(pointsCountInput = newValue, error = null)
    }

    fun onValuesChanged(newValue: String) {
        viewState = viewState.copy(valuesInput = newValue, error = null)
    }

    fun buildGraph() = viewModelScope.launch {
        val count = viewState.pointsCountInput.toIntOrNull()
        if (count == null || count <= 0) {
            showError(ErrorMessages.DOTS_MISMATCH)
            return@launch
        }

        val parsedValues = viewState.valuesInput
            .split(',')
            .mapNotNull { it.trim().toFloatOrNull() }

        if (parsedValues.size != count) {
            showError(ErrorMessages.NOT_MATCHING_VALUE + count)
            return@launch
        }

        if (parsedValues.any { it < 0f }) {
            showError(ErrorMessages.NOT_POSITIVE)
            return@launch
        }

        viewState = viewState.copy(
            points = parsedValues.toImmutableList(),
            error = null
        )
    }

    fun dismissError() {
        viewState = viewState.copy(error = null)
    }

    private fun showError(message: String) {
        viewState = viewState.copy(error = message)
    }
}
