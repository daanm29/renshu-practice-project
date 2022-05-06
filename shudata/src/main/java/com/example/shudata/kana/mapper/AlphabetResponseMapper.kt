package com.example.shudata.kana.mapper

import com.example.shudata.kana.model.AlphabetCharacterExampleResponse
import com.example.shudata.kana.model.AlphabetCharacterResponse
import com.example.shudata.kana.model.AlphabetResponse
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shudomain.practice.model.AlphabetCharacterExample
import com.example.shudomain.practice.model.AlphabetPortion

object AlphabetResponseMapper {

    internal fun AlphabetResponse.toAlphabetPortions(): List<AlphabetPortion> {
        return listOf(
            AlphabetPortion(this.alphabet.contentName, this.alphabet.contentData.map { it.toAlphabetCharacter() }),
            AlphabetPortion(this.alphabetCombinations.contentName, this.alphabetCombinations.contentData.map { it.toAlphabetCharacter() })
        )
    }

    private fun AlphabetCharacterResponse.toAlphabetCharacter(): AlphabetCharacter {
        return AlphabetCharacter(this.japaneseCharacter,
            this.romajiCharacter,
            this.portionDataExamples.map { it.toAlphabetCharacterExample() })
    }

    private fun AlphabetCharacterExampleResponse.toAlphabetCharacterExample(): AlphabetCharacterExample {
        return AlphabetCharacterExample(this.japaneseExample, this.romajiExample, this.meaningExample)
    }
}
