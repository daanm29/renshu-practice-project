package com.example.shudomain.practice.repository

import com.example.shudomain.practice.model.AlphabetPortionData
import io.reactivex.rxjava3.core.Single

interface GetLocalKanaRepository {

    fun getAllHiragana(): Single<List<AlphabetPortionData>>

    fun getAllKatakana(): Single<List<AlphabetPortionData>>
}
