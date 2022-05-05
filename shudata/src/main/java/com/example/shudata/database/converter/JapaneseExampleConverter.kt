package com.example.shudata.database.converter

import androidx.room.TypeConverter
import com.example.shudomain.practice.model.JapaneseExample
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class JapaneseExampleConverter {

    @TypeConverter
    fun fromJapaneseExampleList(japaneseExamples: List<JapaneseExample>): String {
        return Gson().toJson(japaneseExamples)
    }

    @TypeConverter
    fun toJapaneseExampleList(jsonString: String): List<JapaneseExample> {
        val type: Type = object: TypeToken<List<JapaneseExample>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}
