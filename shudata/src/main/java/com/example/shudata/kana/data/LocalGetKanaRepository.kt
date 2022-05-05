package com.example.shudata.kana.data

import com.example.shudata.database.dao.KanaDao
import com.example.shudata.kana.mapper.HiraganaKatakanaMapper.mapToAlphabetPortionData
import com.example.shudomain.practice.model.AlphabetPortionData
import com.example.shudomain.practice.repository.GetLocalKanaRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalGetKanaRepository  @Inject constructor(
    private val kanaDao: KanaDao
) : GetLocalKanaRepository{

    override fun getAllHiragana(): Single<List<AlphabetPortionData>> {
        return Single.create { emitter ->
            emitter.onSuccess(kanaDao.getAllHiragana().map { it.mapToAlphabetPortionData() })
        }
    }

    override fun getAllKatakana(): Single<List<AlphabetPortionData>> {
        return Single.create { emitter ->
            emitter.onSuccess(kanaDao.getAllKatakana().map { it.mapToAlphabetPortionData() })
        }
    }
}
