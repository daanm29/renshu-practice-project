package com.example.shudata.kana.data

import com.example.shudata.BuildConfig
import com.example.shudata.kana.model.AlphabetResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface KanaService {

    @Headers(
        "X-RapidAPI-Host: ${BuildConfig.JAP_HOST_URL}",
        "X-RapidAPI-Key: ${BuildConfig.JAP_API_KEY}"
    )
    @GET("fullhiragana")
    fun getAllHiragana(): Single<AlphabetResponse>

    @Headers(
        "X-RapidAPI-Host: ${BuildConfig.JAP_HOST_URL}",
        "X-RapidAPI-Key: ${BuildConfig.JAP_API_KEY}"
    )
    @GET("fullkatakana")
    fun getAllKatakana(): Single<AlphabetResponse>
}
