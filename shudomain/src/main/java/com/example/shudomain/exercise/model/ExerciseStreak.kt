package com.example.shudomain.exercise.model

import java.util.*

data class ExerciseStreak(
    val startDate: Date,
    val currentDate: Date,
    val streakLength: Int,
)
