package com.example.shudata.database.converter

import androidx.room.TypeConverter
import com.example.shudomain.exercise.model.AlphabetExerciseCharacter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ProgressConverter {

    @TypeConverter
    fun fromAlphabetExerciseCharacter(alphabetExerciseCharacters: ArrayList<AlphabetExerciseCharacter>): String {
        return Gson().toJson(alphabetExerciseCharacters)
    }

    @TypeConverter
    fun toAlphabetExerciseCharacter(jsonString: String): ArrayList<AlphabetExerciseCharacter> {
        val type: Type = object : TypeToken<List<AlphabetExerciseCharacter>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}
