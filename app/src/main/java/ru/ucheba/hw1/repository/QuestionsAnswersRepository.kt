package ru.ucheba.hw1.repository

import ru.ucheba.hw1.model.QuestionsData

object QuestionsAnswersRepository {

    var currentPage = 0;

    var list = mutableListOf(
        QuestionsData(
            question = "Ну что там как?",
            answers = listOf("idk", "Норм", "Кайф", "Такое")
        ),
        QuestionsData(
            question = "Как дела, друг?",
            answers = listOf("Потихоньку", "Вообще супер")
        ),
        QuestionsData(
            question = "Что значит слово 'фижма'?",
            answers = listOf("Каркас для юбки", "Юбка", "Какая еще ФИЖМА?")
        ),
        QuestionsData(
            question = "А 'Абиденжабуралдугапар'?",
            answers = listOf("IDK", "IDK", "IDK", "IDK", "IDK", "IDK","IDK", "IDK","IDK", "IDK","IDK", "IDK","IDK", "WTF")
        ),
        QuestionsData(
            question = "Сколько вопросов еще осталось?",
            answers = listOf("0", "1", "2", "3", "4", "5", "6", "тут неправильна")
        ),
        QuestionsData(
            question = "Вопрос на 203$: что?",
            answers = listOf("да", "нет")
        ),
        QuestionsData(
            question = "Разминка: найди приз",
            answers = listOf("оыоы", "ffdffd", "376376", "dsjdsj", "hhfhfhfh", "eyruyeuuey", "0", "1", "2", "3", "4", "5", "6", "ПРИЗ")
        ),
        QuestionsData(
            question = "Отвечай, если крутой!",
            answers = listOf("пипо", "лол", "кек", "кринж","крутяк", "отстой", "база", "IDK","IDK", "IDK", "IDK", "IDK", "IDK", "IDK","IDK", "IDK","IDK", "IDK","IDK", "IDK","IDK", "IDK")
        ),
        QuestionsData(
            question = "Как звали Илью?",
            answers = listOf("Илья", "саша стрекоза")
        ),
        QuestionsData(
            question = "Это 10 вопрос?",
            answers = listOf("Да", "Нет", "Возможно частично")
        ),
        QuestionsData(
            question = "Сколько стоит человек?",
            answers = listOf("5", "один")
        ),
        QuestionsData(
            question = "Это был ТЕСТ НА ПСИХИКУ",
            answers = listOf("Завалил", "ПРОШЕЛ УРАУРУРАРУ")
        ),
    )

    fun getListQuestions(): List<QuestionsData> = list

    fun allchecked(): Boolean {
        for (b in list) {
            if (b.selected == null) {
                return false
            }
        }
        return true
    }
}
