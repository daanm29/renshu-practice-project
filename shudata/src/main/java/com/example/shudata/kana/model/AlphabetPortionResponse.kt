package com.example.shudata.kana.model

import com.google.gson.annotations.SerializedName

data class AlphabetPortionResponse(

    @SerializedName(value = "name")
    val contentName: String,

    @SerializedName(value = "content")
    val contentData: List<AlphabetCharacterResponse>
)
