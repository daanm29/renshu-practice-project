package com.example.shudata.database

import com.example.shudata.database.entity.HiraganaEntity
import com.example.shudata.database.entity.KatakanaEntity
import com.example.shudomain.practice.model.AlphabetCharacter

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
}
