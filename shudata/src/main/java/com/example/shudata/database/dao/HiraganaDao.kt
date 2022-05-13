package com.example.shudata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shudata.database.entity.HiraganaEntity
import com.example.shudata.database.entity.HiraganaProgressEntity
import com.example.shudomain.practice.model.AlphabetCharacter

@Dao
abstract class HiraganaDao {

    @Query("SELECT * FROM hiragana")
    abstract fun getHiragana(): List<HiraganaEntity>

    @Insert(onConflict = REPLACE)
    abstract fun insertHiragana(hiragana: List<HiraganaEntity>)

    @Query("SELECT * FROM hiragana_progress ORDER BY date DESC")
    abstract fun getHiraganaProgress(): List<HiraganaProgressEntity>

    @Insert(onConflict = REPLACE)
    abstract fun insertHiraganaProgress(hiraganaProgress: HiraganaProgressEntity)

    @Query("DELETE FROM hiragana_progress WHERE date <> (SELECT max(date) FROM hiragana_progress ORDER BY date DESC)")
    abstract fun deleteOldHiraganaExercises()
}
