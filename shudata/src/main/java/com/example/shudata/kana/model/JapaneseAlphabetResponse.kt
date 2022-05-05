package com.example.shudata.kana.model

import com.google.gson.annotations.SerializedName

data class JapaneseAlphabetResponse(

    @SerializedName(value = "0")
    val hiraganaCombinations: AlphabetPortionResponse,

    @SerializedName(value = "1")
    val katakanaCombinations: AlphabetPortionResponse,

    @SerializedName(value = "2")
    val hiragana: AlphabetPortionResponse,

    @SerializedName(value = "3")
    val katakana: AlphabetPortionResponse,
)
