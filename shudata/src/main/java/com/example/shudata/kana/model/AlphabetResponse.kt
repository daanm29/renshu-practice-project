package com.example.shudata.kana.model

import com.google.gson.annotations.SerializedName

data class AlphabetResponse(

    @SerializedName(value = "0")
    val alphabetCombinations: AlphabetPortionResponse,

    @SerializedName(value = "1")
    val alphabet: AlphabetPortionResponse,
)
