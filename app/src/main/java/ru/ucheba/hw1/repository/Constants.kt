package ru.ucheba.hw1.repository

import ru.ucheba.hw1.R
import ru.ucheba.hw1.model.PoolData
import ru.ucheba.hw1.model.RbDataMode
import ru.ucheba.hw1.model.RbDataOption
import ru.ucheba.hw1.utils.CoroutineCancellationMode
import ru.ucheba.hw1.utils.CoroutineExecutionMode
import ru.ucheba.hw1.utils.CoroutinePool

object Constants {
    val listOfPool = listOf(
        PoolData(CoroutinePool.IO, R.string.io),
        PoolData(CoroutinePool.MAIN, R.string.main),
        PoolData(CoroutinePool.UNCONFINED, R.string.unconfined),
        PoolData(CoroutinePool.DEFAULT, R.string.default_mode)
    )

    val listOfModes = listOf(
        RbDataOption(CoroutineExecutionMode.SEQUENTIAL, R.string.seq),
        RbDataOption(CoroutineExecutionMode.PARALLEL, R.string.parralel)
    )

    val listOfOptions = listOf(
        RbDataMode(CoroutineCancellationMode.CANCEL_ON_BACKGROUND, R.string.cancel_back),
        RbDataMode(CoroutineCancellationMode.CONTINUE_ON_BACKGROUND, R.string.continue_then)
    )
}