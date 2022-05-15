package com.example.shudomain.list

import com.example.shudomain.list.model.CustomList
import com.example.shudomain.list.repository.CustomListRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCustomLists @Inject constructor(
    private val customListRepository: CustomListRepository
){

    operator fun invoke(): Single<List<CustomList>> {
        return customListRepository.getCustomLists()
    }
}
