package com.example.shudata.kana.data

import com.example.shudata.database.converter.EntityConverter.toAlphabetCharacter
import com.example.shudata.database.converter.EntityConverter.toHiraganaEntity
import com.example.shudata.database.converter.EntityConverter.toKatakanaEntity
import com.example.shudata.database.dao.HiraganaDao
import com.example.shudata.database.dao.KatakanaDao
import com.example.shudata.kana.mapper.AlphabetResponseMapper.toAlphabetPortions
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shudomain.practice.repository.KanaRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteKanaRepository @Inject constructor(
    private val kanaService: KanaService,
    private val hiraganaDao: HiraganaDao,
    private val katakanaDao: KatakanaDao,
) : KanaRepository {

    override fun getAllHiragana(): Single<List<AlphabetCharacter>> {
        val alphabetHiragana = hiraganaDao.getHiragana()

        return if (alphabetHiragana.isNullOrEmpty()) {
            kanaService.getAllHiragana().map { it }
                .map { alphabetResponse ->
                    val alphabetPortion = alphabetResponse.toAlphabetPortions()
                    val alphabetCharacters = alphabetPortion[0].portionData + alphabetPortion[1].portionData
                    hiraganaDao.insertHiragana(alphabetCharacters.map { it.toHiraganaEntity() })
                    alphabetCharacters
                }
        } else {
            Single.create { emitter ->
                emitter.onSuccess(alphabetHiragana.map { it.toAlphabetCharacter() })
            }
        }
    }

    override fun getAllKatakana(): Single<List<AlphabetCharacter>> {
        val alphabetKatakana = katakanaDao.getKatakana()

        return if (alphabetKatakana.isNullOrEmpty()) {
            kanaService.getAllKatakana().map { it }
                .map { alphabetResponse ->
                    val alphabetPortion = alphabetResponse.toAlphabetPortions()
                    val alphabetCharacters = alphabetPortion[0].portionData + alphabetPortion[1].portionData

                    katakanaDao.insertKatakana(alphabetCharacters.map { it.toKatakanaEntity() })
                    alphabetCharacters
                }
        } else {
            Single.create { emitter ->
                emitter.onSuccess(alphabetKatakana.map { it.toAlphabetCharacter() })
            }
        }
    }
}
