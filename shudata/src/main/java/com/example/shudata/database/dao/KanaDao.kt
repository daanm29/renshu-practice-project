package com.example.shudata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shudata.database.entity.Hiragana
import com.example.shudata.database.entity.Katakana

@Dao
interface KanaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHiragana(hiragana: Hiragana)

    @Query("SELECT * FROM hiragana")
    fun getAllHiragana(): List<Hiragana>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllKatakana(hiragana: Hiragana)

    @Query("SELECT * FROM katakana")
    fun getAllKatakana(): List<Katakana>

}
