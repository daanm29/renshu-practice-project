package com.example.shudomain.list.repository

import com.example.shudomain.list.model.CustomList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CustomListRepository {

    fun getCustomLists(): Single<List<CustomList>>

    fun saveCustomList(customList: CustomList) : Completable

    fun deleteCustomList(title: String): Completable
}
