package com.example.shudomain.exercise.repository

import com.example.shudomain.exercise.model.AlphabetExercise
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface GetKanaExercisesRepository {

    fun getAllHiraganaExercises(): Single<AlphabetExercise>

    fun saveHiraganaExercise(alphabetExercise: AlphabetExercise): Completable

    fun getAllKatakanaExercise(): Single<AlphabetExercise>

    fun saveKatakanaExercise(alphabetExercise: AlphabetExercise): Completable
}
