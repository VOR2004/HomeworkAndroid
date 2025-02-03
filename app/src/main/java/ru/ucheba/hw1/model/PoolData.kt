package ru.ucheba.hw1.model

import androidx.annotation.StringRes
import ru.ucheba.hw1.utils.CoroutinePool

class PoolData(
    val thread: CoroutinePool? = null,
    @StringRes
    val name: Int
)