package com.example.shudata.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shudata.database.AlphabetExampleConverter
import com.example.shudomain.practice.model.AlphabetCharacterExample

@Entity(tableName = "katakana")
@TypeConverters(AlphabetExampleConverter::class)
data class KatakanaEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "kata_char") val katakanaCharacter: String,
    @ColumnInfo(name = "roma_char") val romajiCharacter: String,
    @ColumnInfo(name = "kata_exa") val katakanaExamples: List<AlphabetCharacterExample>,
)
