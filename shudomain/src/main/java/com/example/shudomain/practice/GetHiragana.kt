package com.example.shudomain.practice

import com.example.shudomain.practice.model.AlphabetPortionData
import com.example.shudomain.practice.model.AlphabetType
import com.example.shudomain.practice.repository.GetKanaRepository
import com.example.shudomain.practice.repository.GetLocalKanaRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetHiragana @Inject constructor(
    private val getKanaRepository: GetKanaRepository,
    private val kanaLocalRepository: GetLocalKanaRepository,
) {

    operator fun invoke(alphabet: AlphabetType): Single<List<AlphabetPortionData>> {
        return if (alphabet == AlphabetType.HIRAGANA) {
            kanaLocalRepository.getAllHiragana()
        } else {
            kanaLocalRepository.getAllKatakana()
        }
    }
}
