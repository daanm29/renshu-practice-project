package com.example.shudata.kana.mapper

import com.example.shudata.kana.model.AlphabetPortionDataResponse
import com.example.shudata.kana.model.JapaneseAlphabetResponse
import com.example.shudata.kana.model.JapaneseExampleResponse
import com.example.shudomain.practice.model.AlphabetPortion
import com.example.shudomain.practice.model.AlphabetPortionData
import com.example.shudomain.practice.model.JapaneseExample

object JapaneseAlphabetResponseMapper {

    internal fun JapaneseAlphabetResponse.mapToAlphabetPortion(): List<AlphabetPortion> {
        return listOf(
            AlphabetPortion(this.hiraganaCombinations.contentName, this.hiraganaCombinations.contentData
                .map { it.mapToAlphabetPortionData() }),
            AlphabetPortion(this.katakanaCombinations.contentName, this.katakanaCombinations.contentData
                .map { it.mapToAlphabetPortionData() }),
            AlphabetPortion(this.hiragana.contentName, this.hiragana.contentData
                .map { it.mapToAlphabetPortionData() }),
            AlphabetPortion(this.katakana.contentName, this.katakana.contentData
                .map { it.mapToAlphabetPortionData() }),
        )
    }

    private fun AlphabetPortionDataResponse.mapToAlphabetPortionData(): AlphabetPortionData {
        return AlphabetPortionData(this.japaneseCharacter, this.romajiCharacter, this.portionDataExamples.map { it.mapToJapaneseExample() })
    }

    private fun JapaneseExampleResponse.mapToJapaneseExample(): JapaneseExample {
        return JapaneseExample(this.japaneseExample, this.romajiExample, this.meaningExample)
    }
}
