package com.example.shupresentation.generic.mvvm

import androidx.lifecycle.MutableLiveData
import com.example.shupresentation.generic.mvvm.UIState.*
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

object RxSingleExtension {

    internal fun <T : Any> Single<T>.postUIStateTo(uiState: MutableLiveData<UIState>): Single<T> {
        return doOnSubscribe { uiState.postValue(LOADING) }
            .doOnSuccess { uiState.postValue(NORMAL) }
            .doOnError { uiState.postValue(ERROR) }
    }

    internal fun <T : Any> Single<T>.subscribeOnIO(): Single<T> {
        return subscribeOn(Schedulers.io())
    }

    internal fun <T : Any> Single<T>.observeOnMain(): Single<T> {
        return observeOn(Schedulers.computation())
    }
}
