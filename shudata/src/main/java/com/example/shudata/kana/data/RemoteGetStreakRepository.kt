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

    override fun getStreaks(): Single<List<ExerciseStreak>> {
        val currentStreak = streakDao.getCurrentStreak()

        return Single.create { emitter ->
            emitter.onSuccess(currentStreak.map { it.toExerciseStreak() })
        }
    }

    override fun insertCurrentStreak(streak: ExerciseStreak) : Completable {
        return Completable.create { emitter ->
            streakDao.insertStreak(streak.toStreakEntity())
            emitter.onComplete()
        }
    }
}
