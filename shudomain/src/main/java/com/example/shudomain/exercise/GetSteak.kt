package com.example.shudomain.exercise

import com.example.shudomain.exercise.model.ExerciseStreak
import com.example.shudomain.exercise.repository.StreakRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetSteak @Inject constructor(
    private val streakRepository: StreakRepository
) {

    operator fun invoke(): Single<List<ExerciseStreak>> {
        return streakRepository.getStreaks()
    }
}
