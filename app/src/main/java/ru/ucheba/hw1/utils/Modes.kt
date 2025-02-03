package ru.ucheba.hw1.utils

enum class CoroutinePool {
    IO,
    DEFAULT,
    UNCONFINED,
    MAIN
}
enum class CoroutineExecutionMode {
    SEQUENTIAL,
    PARALLEL
}
enum class CoroutineCancellationMode {
    CANCEL_ON_BACKGROUND,
    CONTINUE_ON_BACKGROUND
}