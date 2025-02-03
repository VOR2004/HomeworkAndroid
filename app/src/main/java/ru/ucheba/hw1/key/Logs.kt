package ru.ucheba.hw1.key

object Logs {

    fun logCoroutineFinished(index: Int, name: String): String {
        return "Coroutine $index finished on thread: $name"
    }
    fun logCoroutineCanceled(index: Int): String {
        return "Coroutine $index canceled"
    }
    fun logCanceled(count: Int): String {
        return "Отменено корутин: $count"
    }
}