package com.example.shudomain.exercise.repository

import com.example.shudomain.exercise.model.AlphabetExercise
import io.reactivex.rxjava3.core.Single

interface GetKanaExercisesRepository {

    fun getAllHiraganaExercises(): Single<AlphabetExercise>

    fun getAllKatakanaExercise(): Single<AlphabetExercise>
}
