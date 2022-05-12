package com.example.shudata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shudata.database.entity.StreakEntity

@Dao
abstract class StreakDao {

    @Query("SELECT * FROM streak WHERE streak_current BETWEEN date(:from) AND date(:to)")
    abstract fun getCurrentStreak(
        from: Long? = System.currentTimeMillis().minus(DAY_IN_MILLISECONDS),
        to: Long? = System.currentTimeMillis(),
    ): StreakEntity?

    @Insert(onConflict = REPLACE)
    abstract fun insertStreak(streakEntity: StreakEntity)

    companion object {

        private const val DAY_IN_MILLISECONDS = 8_640_000_000L
    }
}
