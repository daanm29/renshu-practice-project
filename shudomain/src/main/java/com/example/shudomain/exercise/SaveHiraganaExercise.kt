package com.example.shudomain.exercise

import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.repository.GetKanaExercisesRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SaveHiraganaExercise @Inject constructor(
    private val kanaExercisesRepository: GetKanaExercisesRepository,
){

    operator fun invoke(alphabetExercise: AlphabetExercise): Completable {
        return kanaExercisesRepository.saveHiraganaExercise(alphabetExercise)
    }
}
