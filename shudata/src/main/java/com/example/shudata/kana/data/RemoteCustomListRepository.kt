package com.example.shudata.kana.data

import com.example.shudata.database.converter.EntityConverter.toCustomList
import com.example.shudata.database.converter.EntityConverter.toCustomListEntity
import com.example.shudata.database.dao.CustomListDao
import com.example.shudomain.list.repository.CustomListRepository
import com.example.shudomain.list.model.CustomList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteCustomListRepository @Inject constructor(
    private val customListDao: CustomListDao,
) : CustomListRepository {

    override fun getCustomLists(): Single<List<CustomList>> {
        return Single.create { emitter ->
            val customList = customListDao.getCustomLists()

            if (customList.isNullOrEmpty()) {
                emitter.onSuccess(emptyList())
            } else {
                emitter.onSuccess(customList.map { it.toCustomList() })
            }
        }
    }

    override fun getCustomList(title: String): Single<CustomList> {
        return Single.create { emitter ->
            emitter.onSuccess(customListDao.getCustomList(title).toCustomList())
        }
    }

    override fun saveCustomList(customList: CustomList): Completable {
        return Completable.create { emitter ->
            customListDao.insertCustomList(customList.toCustomListEntity())
            emitter.onComplete()
        }
    }

    override fun deleteCustomList(title: String): Completable {
        return Completable.create { emitter ->
            customListDao.deleteCustomList(title)
            emitter.onComplete()
        }
    }
}
