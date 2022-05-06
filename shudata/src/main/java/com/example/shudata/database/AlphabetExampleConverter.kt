package com.example.shudata.database

import androidx.room.TypeConverter
import com.example.shudomain.practice.model.AlphabetCharacterExample
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AlphabetExampleConverter {

    @TypeConverter
    fun fromCharacterExamples(characterExamples: List<AlphabetCharacterExample>): String {
        return Gson().toJson(characterExamples)
    }

    @TypeConverter
    fun toCharacterExamples(jsonString: String): List<AlphabetCharacterExample> {
        val type: Type = object : TypeToken<List<AlphabetCharacterExample>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}
