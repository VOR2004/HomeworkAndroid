package ru.itis.myapplication.app.utils.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventSender {
    private val _events = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    fun send(event: NavigationEvent) {
        _events.tryEmit(event)
    }
}