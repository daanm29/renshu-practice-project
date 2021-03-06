package com.example.shudata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shudata.database.entity.KatakanaEntity
import com.example.shudata.database.entity.KatakanaProgressEntity
import com.example.shudomain.practice.model.AlphabetCharacter

@Dao
abstract class KatakanaDao {

    @Query("SELECT * FROM katakana")
    abstract fun getKatakana(): List<KatakanaEntity>

    @Insert(onConflict = REPLACE)
    abstract fun insertKatakana(hiragana: List<KatakanaEntity>)

    @Query("SELECT * FROM katakana_progress ORDER BY date DESC")
    abstract fun getKatakanaProgress(): List<KatakanaProgressEntity>

    @Insert(onConflict = REPLACE)
    abstract fun insertKatakanaProgress(katakanaProgress: KatakanaProgressEntity)

    @Query("DELETE FROM katakana_progress WHERE date <> (SELECT max(date) FROM katakana_progress ORDER BY date DESC)")
    abstract fun deleteOldHiraganaExercises()
}
