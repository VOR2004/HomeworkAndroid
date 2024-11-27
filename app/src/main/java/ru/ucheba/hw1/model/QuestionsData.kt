package ru.ucheba.hw1.model

import ru.ucheba.hw1.utils.StringUtils

data class QuestionsData(
    val id: String = StringUtils().getRandomString(100),
    val question: String,
    val answers: List<String>,
    var selected: Int? = null,
)
