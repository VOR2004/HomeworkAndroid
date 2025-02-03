package ru.ucheba.hw1.model

import androidx.annotation.StringRes
import ru.ucheba.hw1.utils.CoroutineExecutionMode

class RbDataOption(
    val option: CoroutineExecutionMode? = null,
    @StringRes
    val name: Int
)