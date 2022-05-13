package com.example.shudata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shudata.database.entity.StreakEntity

@Dao
abstract class StreakDao {

    @Query("SELECT * FROM streak ORDER BY streak_current DESC")
    abstract fun getCurrentStreak(): List<StreakEntity>

    @Insert(onConflict = REPLACE)
    abstract fun insertStreak(streakEntity: StreakEntity)
}
