package com.example.shudomain.practice

import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shudomain.practice.repository.KanaRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAllHiragana @Inject constructor(
    private val kanaRepository: KanaRepository,
) {

    operator fun invoke(): Single<List<AlphabetCharacter>> {
        return kanaRepository.getAllHiragana()
    }
}
