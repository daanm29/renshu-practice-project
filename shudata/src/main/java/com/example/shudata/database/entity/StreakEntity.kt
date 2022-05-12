package com.example.shudata.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shudata.database.DateConverter
import java.util.*

@Entity(tableName = "streak")
@TypeConverters(DateConverter::class)
data class StreakEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "streak_start") val startDate: Date,
    @ColumnInfo(name = "streak_length") var streakLength: Int,
    @ColumnInfo(name = "streak_current") var currentDate: Date,
)
