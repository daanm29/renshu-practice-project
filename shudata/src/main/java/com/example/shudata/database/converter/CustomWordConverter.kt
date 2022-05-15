package com.example.shudata.database.converter

import androidx.room.TypeConverter
import com.example.shudomain.list.model.CustomListWord
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object CustomWordConverter {

    @TypeConverter
    fun fromCustomListWords(customListWord: List<CustomListWord>): String {
        return Gson().toJson(customListWord)
    }

    @TypeConverter
    fun toCustomListWords(jsonString: String): List<CustomListWord> {
        val type: Type = object : TypeToken<List<CustomListWord>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}
