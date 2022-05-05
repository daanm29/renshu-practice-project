package com.example.shudata.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shudata.database.converter.JapaneseExampleConverter
import com.example.shudomain.practice.model.JapaneseExample

@Entity(tableName = "katakana")
@TypeConverters(JapaneseExampleConverter::class)
data class Katakana(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "japanese") val japaneseChar: String,
    @ColumnInfo(name = "romaji") val romajiChar: String,
    @ColumnInfo(name = "examples") val examples: List<JapaneseExample>,
)
