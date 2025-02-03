package ru.ucheba.hw1.model

import androidx.annotation.StringRes
import ru.ucheba.hw1.utils.CoroutineCancellationMode

class RbDataMode(
    val mode: CoroutineCancellationMode? = null,
    @StringRes
    val name: Int
)