package ru.ucheba.hw1.repository

import ru.ucheba.hw1.R
import ru.ucheba.hw1.model.ThemeData

object ThemeRepository {
    val themes = listOf(
        ThemeData(R.color.red_default, R.style.Theme_Style_Red),
        ThemeData(R.color.green_default, R.style.Theme_Style_Green),
        ThemeData(R.color.yellow_default, R.style.Theme_Style_Yellow)
    )
}