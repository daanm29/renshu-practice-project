package com.example.shudata.kana.data

import com.example.shudata.kana.mapper.AlphabetResponseMapper.toAlphabetPortions
import com.example.shudomain.practice.model.AlphabetPortion
import com.example.shudomain.practice.repository.GetKanaRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteGetKanaRepository @Inject constructor(
    private val kanaService: KanaService,
) : GetKanaRepository {

    override fun getAllHiragana(): Single<List<AlphabetPortion>> {
        return kanaService.getAllHiragana().map { alphabetResponse -> alphabetResponse }
            .map { alphabet ->
                alphabet.toAlphabetPortions()
            }
    }

    override fun getAllKatakana(): Single<List<AlphabetPortion>> {
        return kanaService.getAllKatakana().map { alphabetResponse -> alphabetResponse }
            .map { alphabet ->
                alphabet.toAlphabetPortions()
            }
    }
}
