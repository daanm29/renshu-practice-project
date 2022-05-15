package com.example.shudomain.exercise

import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.repository.KanaExerciseRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetKatakanaExercises @Inject constructor(
    private val kanaExerciseRepository: KanaExerciseRepository
) {

    operator fun invoke(): Single<AlphabetExercise> {
        return kanaExerciseRepository.getAllKatakanaExercise()
    }
}
