package com.example.shudomain.practice

import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shudomain.practice.repository.GetKanaRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAllKatakana @Inject constructor(
    private val kanaRepository: GetKanaRepository,
) {

    operator fun invoke(): Single<List<AlphabetCharacter>> {
        return kanaRepository.getAllKatakana().map { alphabetPortions ->
            val katakanaDataset = alphabetPortions[0].portionData + alphabetPortions[1].portionData
            katakanaDataset
        }
    }
}
