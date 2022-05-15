package com.example.shudomain.list

import com.example.shudomain.list.model.CustomList
import com.example.shudomain.list.repository.CustomListRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SaveCustomList @Inject constructor(
    private val customListRepository: CustomListRepository
) {

    operator fun invoke(customList: CustomList) : Completable {
        return customListRepository.saveCustomList(customList)
    }
}
