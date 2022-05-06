package com.example.shudomain.practice.model

data class AlphabetCharacter(
    val japaneseCharacter: String,
    val romajiCharacter: String, // String because べ is [be]
    val japaneseExamples: List<AlphabetCharacterExample>
)
