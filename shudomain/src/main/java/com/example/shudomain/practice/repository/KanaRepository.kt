package com.example.shudomain.practice.repository

import com.example.shudomain.practice.model.AlphabetCharacter
import io.reactivex.rxjava3.core.Single

interface KanaRepository {

    fun getAllHiragana(): Single<List<AlphabetCharacter>>

    fun getAllKatakana(): Single<List<AlphabetCharacter>>
}
