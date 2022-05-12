package com.example.shudata.kana.data

import com.example.shudata.database.EntityConverter.toExerciseStreak
import com.example.shudata.database.EntityConverter.toStreakEntity
import com.example.shudata.database.dao.StreakDao
import com.example.shudomain.exercise.model.ExerciseStreak
import com.example.shudomain.exercise.repository.GetStreakRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteGetStreakRepository @Inject constructor(
    private val streakDao: StreakDao
) : GetStreakRepository {

    override fun getCurrentStreak(): Single<ExerciseStreak> {
        val currentStreak = streakDao.getCurrentStreak()

        return Single.create { emitter ->
            if (currentStreak != null) {
                emitter.onSuccess(currentStreak.toExerciseStreak())
            }
        }
    }

    override fun insertCurrentStreak(streak: ExerciseStreak) : Completable {
        return Completable.create { emitter ->
            streakDao.insertStreak(streak.toStreakEntity())
            emitter.onComplete()
        }
    }
}
