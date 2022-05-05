package com.example.shudata.kana.model

import com.google.gson.annotations.SerializedName

data class AlphabetPortionDataResponse(

    @SerializedName(value = "japanese")
    val japaneseCharacter: String,

    @SerializedName(value = "romaji")
    val romajiCharacter: String,

    @SerializedName(value = "examples")
    val portionDataExamples: List<JapaneseExampleResponse>
)
