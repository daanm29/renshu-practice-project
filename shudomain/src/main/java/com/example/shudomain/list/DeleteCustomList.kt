package com.example.shudomain.list

import com.example.shudomain.list.repository.CustomListRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteCustomList @Inject constructor(
    private val customListRepository: CustomListRepository
) {

    operator fun invoke(title: String): Completable {
        return customListRepository.deleteCustomList(title)
    }
}
