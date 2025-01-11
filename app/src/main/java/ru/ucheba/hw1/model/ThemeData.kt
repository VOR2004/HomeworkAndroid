package ru.ucheba.hw1.model

class ThemeData(
    private val color: Int,
    private val style: Int
) {
    fun getColor(): Int {
        return color
    }

    fun getStyle(): Int {
        return style
    }
}