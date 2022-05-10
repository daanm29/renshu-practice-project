package com.example.shudomain.exercise.model

data class AlphabetExercise(
    val currentItem: Int,
    val exercisesDone: ArrayList<AlphabetExerciseCharacter>,
    val exercisesTodo: ArrayList<AlphabetExerciseCharacter>,
    var completed: Boolean = false
)
