package com.example.shupresentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.home.HomeNavigationAction.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<HomeNavigationAction>()
    val navigation: LiveData<HomeNavigationAction> = _navigation

    fun openHiraganaLastPractice() {
        _navigation.postValue(OpenHiraganaPractice)
    }

    fun openKatakanaLastPractice() {
        _navigation.postValue(OpenRecentKatakana)
    }

    fun openListLastPractice() {
        _navigation.postValue(OpenRecentListPractice)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
