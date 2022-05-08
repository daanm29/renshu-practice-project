package com.example.shupresentation.practice.alphabet

sealed class AlphabetNavigationAction {

    data class OpenOtherCharacter(
        val character: String,
    ) : AlphabetNavigationAction()
}
