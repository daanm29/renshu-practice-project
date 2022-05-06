package com.example.shudata.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shudata.database.AlphabetExampleConverter
import com.example.shudomain.practice.model.AlphabetCharacterExample

@Entity(tableName = "hiragana")
@TypeConverters(AlphabetExampleConverter::class)
data class HiraganaEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "hira_char") val hiraganaCharacter: String,
    @ColumnInfo(name = "roma_char") val romajiCharacter: String,
    @ColumnInfo(name = "hira_exa") val hiraganaExamples: List<AlphabetCharacterExample>,
)
