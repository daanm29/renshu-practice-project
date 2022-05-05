package com.example.shudomain.practice.model

data class AlphabetPortionData(
    val japaneseCharacter: String,
    val romajiCharacter: String, // String because べ is [be]
    val japaneseExamples: List<JapaneseExample>
)
