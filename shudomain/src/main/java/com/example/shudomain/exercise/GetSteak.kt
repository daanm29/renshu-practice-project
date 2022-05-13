package com.example.shudomain.exercise

import com.example.shudomain.exercise.model.ExerciseStreak
import com.example.shudomain.exercise.repository.GetStreakRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetSteak @Inject constructor(
    private val getStreakRepository: GetStreakRepository
) {

    operator fun invoke(): Single<List<ExerciseStreak>> {
        return getStreakRepository.getStreaks()
    }
}
