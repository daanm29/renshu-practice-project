package com.example.shudata.database

import com.example.shudata.database.entity.HiraganaEntity
import com.example.shudata.database.entity.HiraganaProgressEntity
import com.example.shudata.database.entity.KatakanaEntity
import com.example.shudata.database.entity.KatakanaProgressEntity
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shudomain.exercise.model.AlphabetExercise
import java.util.*

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
            completed = this.completed
        )
    }

    internal fun AlphabetExercise.toHiraganaProgressEntity(): HiraganaProgressEntity {
        return HiraganaProgressEntity(
            currentItem = this.currentItem,
            exercisesDone = this.exercisesDone,
            exercisesTodo = this.exercisesTodo,
            practiceData = Date(),
            completed = this.completed
        )
    }

    internal fun KatakanaProgressEntity.toAlphabetExercise(): AlphabetExercise {
        return AlphabetExercise(
            currentItem = this.currentItem,
            exercisesDone = this.exercisesDone,
            exercisesTodo = this.exercisesTodo,
            completed = this.completed
        )
    }

    internal fun AlphabetExercise.toKatakanaProgressEntity(): KatakanaProgressEntity {
        return KatakanaProgressEntity(
            currentItem = this.currentItem,
            exercisesDone = this.exercisesDone,
            exercisesTodo = this.exercisesTodo,
            practiceData = Date(),
            completed = this.completed
        )
    }
}
