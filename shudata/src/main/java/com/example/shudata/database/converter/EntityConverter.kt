package com.example.shudata.database.converter

import com.example.shudata.database.entity.*
import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.model.ExerciseStreak
import com.example.shudomain.list.model.CustomList
import com.example.shudomain.list.model.CustomListWord
import com.example.shudomain.practice.model.AlphabetCharacter
import java.util.*
import kotlin.collections.ArrayList

object EntityConverter {

    internal fun KatakanaEntity.toAlphabetCharacter(): AlphabetCharacter {
        return AlphabetCharacter(this.katakanaCharacter, this.romajiCharacter, this.katakanaExamples)
    }

    internal fun AlphabetCharacter.toKatakanaEntity(): KatakanaEntity {
        return KatakanaEntity(
            katakanaCharacter = this.japaneseCharacter,
            romajiCharacter = this.romajiCharacter,
            katakanaExamples = this.japaneseExamples,
        )
    }

    internal fun HiraganaEntity.toAlphabetCharacter(): AlphabetCharacter {
        return AlphabetCharacter(this.hiraganaCharacter, this.romajiCharacter, this.hiraganaExamples)
    }

    internal fun AlphabetCharacter.toHiraganaEntity(): HiraganaEntity {
        return HiraganaEntity(
            hiraganaCharacter = this.japaneseCharacter,
            romajiCharacter = this.romajiCharacter,
            hiraganaExamples = this.japaneseExamples,
        )
    }

    internal fun HiraganaProgressEntity.toAlphabetExercise(): AlphabetExercise {
        return AlphabetExercise(
            currentItem = this.currentItem,
            exercisesDone = this.exercisesDone,
            exercisesTodo = this.exercisesTodo,
            completed = this.completed,
            mastered = this.mastered,
        )
    }

    internal fun AlphabetExercise.toHiraganaProgressEntity(): HiraganaProgressEntity {
        return HiraganaProgressEntity(
            currentItem = this.currentItem,
            exercisesDone = this.exercisesDone,
            exercisesTodo = this.exercisesTodo,
            practiceData = Date(),
            completed = this.completed,
            mastered = this.mastered,
        )
    }

    internal fun KatakanaProgressEntity.toAlphabetExercise(): AlphabetExercise {
        return AlphabetExercise(
            currentItem = this.currentItem,
            exercisesDone = this.exercisesDone,
            exercisesTodo = this.exercisesTodo,
            completed = this.completed,
            mastered = this.mastered,
        )
    }

    internal fun AlphabetExercise.toKatakanaProgressEntity(): KatakanaProgressEntity {
        return KatakanaProgressEntity(
            currentItem = this.currentItem,
            exercisesDone = this.exercisesDone,
            exercisesTodo = this.exercisesTodo,
            practiceData = Date(),
            completed = this.completed,
            mastered = this.mastered,
        )
    }

    internal fun StreakEntity.toExerciseStreak(): ExerciseStreak {
        return ExerciseStreak(
            startDate = this.startDate,
            currentDate = this.currentDate,
            streakLength = this.streakLength,
        )
    }

    internal fun ExerciseStreak.toStreakEntity(): StreakEntity {
        return StreakEntity(
            startDate = this.startDate,
            currentDate = this.currentDate,
            streakLength = this.streakLength
        )
    }

    internal fun CustomListEntity.toCustomList(): CustomList {
        return CustomList(
            title = this.titleList,
            description = this.descriptionList,
            characters = this.customWords as ArrayList<CustomListWord>
        )
    }

    internal fun CustomList.toCustomListEntity(): CustomListEntity {
        return CustomListEntity(
            titleList = this.title,
            descriptionList = this.description,
            customWords = this.characters
        )
    }
}
