package com.example.shudata.kana.mapper

import com.example.shudata.database.entity.Hiragana
import com.example.shudata.database.entity.Katakana
import com.example.shudomain.practice.model.AlphabetPortion
import com.example.shudomain.practice.model.AlphabetPortionData

object HiraganaKatakanaMapper {

    internal fun Hiragana.mapToAlphabetPortionData(): AlphabetPortionData {
        return AlphabetPortionData(this.japaneseChar, this.romajiChar, this.examples)
    }

    internal fun Katakana.mapToAlphabetPortionData(): AlphabetPortionData {
        return AlphabetPortionData(this.japaneseChar, this.romajiChar, this.examples)
    }
}
