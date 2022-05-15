package com.example.shudomain.exercise

import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.repository.KanaExerciseRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SaveKatakanaExercise @Inject constructor(
    private val kanaExerciseRepository: KanaExerciseRepository,
) {

    operator fun invoke(alphabetExercise: AlphabetExercise): Completable {
        return kanaExerciseRepository.saveKatakanaExercise(alphabetExercise)
    }
}
