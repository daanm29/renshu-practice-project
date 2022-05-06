package com.example.shudata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shudata.database.entity.HiraganaEntity

@Dao
abstract class HiraganaDao {

    @Query("SELECT * FROM hiragana")
    abstract fun getHiragana(): List<HiraganaEntity>

    @Insert(onConflict = REPLACE)
    abstract fun insertHiragana(hiragana: List<HiraganaEntity>)
}
