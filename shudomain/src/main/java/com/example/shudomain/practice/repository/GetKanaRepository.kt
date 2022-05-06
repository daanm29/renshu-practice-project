package com.example.shudomain.practice.repository

import com.example.shudomain.practice.model.AlphabetPortion
import io.reactivex.rxjava3.core.Single

interface GetKanaRepository {

    fun getAllHiragana(): Single<List<AlphabetPortion>>

    fun getAllKatakana(): Single<List<AlphabetPortion>>
}
