package ru.ucheba.hw1.model

sealed class MultipleHoldersData(
    open val id: String,
    open val headerText: String,
)

class FirstHolderData(
    override val id: String,
    override val headerText: String,
) : MultipleHoldersData(id, headerText)

class SecondHolderData(
    override val id: String,
    override val headerText: String,
    val descText: String,
    val imageUrl: String,
) : MultipleHoldersData(id, headerText)