package com.example.shudata.kana.model

import com.google.gson.annotations.SerializedName

data class JapaneseExampleResponse(

    @SerializedName(value = "japanese")
    val japaneseExample: String?,

    @SerializedName(value = "romaji")
    val romajiExample: String?,

    @SerializedName(value = "meaning")
    val meaningExample: String?
)
