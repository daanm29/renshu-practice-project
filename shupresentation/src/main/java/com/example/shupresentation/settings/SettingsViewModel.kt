package com.example.shupresentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.settings.SettingsNavigationAction.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<SettingsNavigationAction>()
    val navigation: LiveData<SettingsNavigationAction> = _navigation

    fun onOpenAppInfo() {
        _navigation.postValue(OpenAppInfo)
    }

    fun onOpenNotificationSettings() {
        _navigation.postValue(OpenNotificationSettings)
    }

    fun onOpenLicenses() {
        _navigation.postValue(OpenLicenses)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
