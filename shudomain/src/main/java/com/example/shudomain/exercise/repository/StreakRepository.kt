package com.example.shudomain.exercise.repository

import com.example.shudomain.exercise.model.ExerciseStreak
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface StreakRepository {

    fun getStreaks(): Single<List<ExerciseStreak>>

    fun insertCurrentStreak(streak: ExerciseStreak): Completable
}
