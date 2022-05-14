package com.example.shudata.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shudata.database.DateConverter
import com.example.shudata.database.ProgressConverter
import com.example.shudomain.exercise.model.AlphabetExerciseCharacter
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "katakana_progress")
@TypeConverters(ProgressConverter::class, DateConverter::class)
data class KatakanaProgressEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "current") val currentItem: Int,
    @ColumnInfo(name = "done") val exercisesDone: ArrayList<AlphabetExerciseCharacter>,
    @ColumnInfo(name = "todo") val exercisesTodo: ArrayList<AlphabetExerciseCharacter>,
    @ColumnInfo(name = "date") val practiceData: Date,
    @ColumnInfo(name = "completed") val completed: Boolean,
    @ColumnInfo(name = "mastered") val mastered: Int,
)
