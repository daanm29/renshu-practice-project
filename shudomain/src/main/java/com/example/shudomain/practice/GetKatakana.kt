package com.example.shudomain.practice

import com.example.shudomain.practice.model.AlphabetPortion
import com.example.shudomain.practice.model.AlphabetType
import com.example.shudomain.practice.repository.GetKanaRepository
import com.example.shudomain.practice.repository.GetLocalKanaRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetKatakana @Inject constructor(
    private val kanaRepository: GetKanaRepository,
    private val kanaLocalRepository: GetLocalKanaRepository,
) {

    operator fun invoke(alphabet: AlphabetType): Single<List<AlphabetPortion>> {
        return kanaRepository.getAllKana()
    }
}
