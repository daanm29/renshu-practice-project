package com.example.shudomain.practice.repository

import com.example.shudomain.practice.model.AlphabetPortion
import com.example.shudomain.practice.model.AlphabetType
import io.reactivex.rxjava3.core.Single

interface GetKanaRepository {

    fun getAllKana(): Single<List<AlphabetPortion>>
}
