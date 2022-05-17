package com.example.shupresentation.practice.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shudomain.list.DeleteCustomList
import com.example.shudomain.list.GetCustomList
import com.example.shudomain.list.model.CustomList
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.generic.mvvm.RxSingleExtension.observeOnMain
import com.example.shupresentation.generic.mvvm.RxSingleExtension.subscribeOnIO
import com.example.shupresentation.practice.list.ListOverviewNavigationAction.OpenPractice
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class ListOverviewViewModel @Inject constructor(
    private val listOverviewViewModelArguments: ListOverviewViewModelArguments,
    private val getCustomList: GetCustomList,
    private val deleteCustomList: DeleteCustomList,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<ListOverviewNavigationAction>()
    val navigation: LiveData<ListOverviewNavigationAction> = _navigation

    private val _customList = MutableLiveData<CustomList>()
    val customList: LiveData<CustomList> by lazy {
        getList(listOverviewViewModelArguments.listTitle)
        _customList
    }

    private fun getList(title: String) {
        getCustomList(title)
            .subscribeOnIO()
            .observeOnMain()
            .subscribe(_customList::postValue, Timber::e)
            .addTo(compositeDisposable)
    }

    fun deleteList(title: String) {
        deleteCustomList(title)
            .subscribe({
                _navigation.postValue(OpenPractice)
            }, Timber::e)
            .addTo(compositeDisposable)
    }

    fun openPractice() {
        _navigation.postValue(OpenPractice)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
