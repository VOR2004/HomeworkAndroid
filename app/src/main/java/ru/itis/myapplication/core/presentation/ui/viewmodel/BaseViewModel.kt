package ru.itis.myapplication.core.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State : Any>(
    initState: State
) : ViewModel() {

    private val _viewState = MutableStateFlow(initState)

    protected var viewState: State
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    val viewStates: StateFlow<State>
        get() = _viewState.asStateFlow()
}