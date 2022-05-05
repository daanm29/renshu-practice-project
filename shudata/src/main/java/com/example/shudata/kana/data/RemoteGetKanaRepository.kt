package com.example.shudata.kana.data

import com.example.shudata.kana.mapper.JapaneseAlphabetResponseMapper.mapToAlphabetPortion
import com.example.shudomain.practice.model.AlphabetPortion
import com.example.shudomain.practice.repository.GetKanaRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteGetKanaRepository @Inject constructor(
    private val kanaService: KanaService,
) : GetKanaRepository {

    override fun getAllKana(): Single<List<AlphabetPortion>> {
        return kanaService.getAllKana().map { it.mapToAlphabetPortion() }
    }
}
