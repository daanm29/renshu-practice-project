package com.example.shudomain.exercise

import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.repository.GetKanaExercisesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetHiraganaExercises @Inject constructor(
    private val kanaExercisesRepository: GetKanaExercisesRepository
) {

    operator fun invoke(): Single<AlphabetExercise> {
        return kanaExercisesRepository.getAllHiraganaExercises()
    }
}
