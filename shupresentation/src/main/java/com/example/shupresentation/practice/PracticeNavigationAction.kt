package com.example.shupresentation.practice

sealed class PracticeNavigationAction {

    data class OpenHiragana(
        val character: String,
    ) : PracticeNavigationAction()

    data class OpenKatakana(
        val character: String,
    ) : PracticeNavigationAction()

    data class OpenList(
        val listId: String,
    ) : PracticeNavigationAction()
}
