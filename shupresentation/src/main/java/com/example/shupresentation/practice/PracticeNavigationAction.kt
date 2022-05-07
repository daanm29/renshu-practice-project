package com.example.shupresentation.practice

sealed class PracticeNavigationAction {

    data class OpenHiragana(
        val character: String,
    ) : PracticeNavigationAction()

    object OpenHiraganaPractice : PracticeNavigationAction()

    data class OpenKatakana(
        val character: String,
    ) : PracticeNavigationAction()

    object OpenKatakanaPractice: PracticeNavigationAction()

    data class OpenList(
        val listId: String,
    ) : PracticeNavigationAction()
}
