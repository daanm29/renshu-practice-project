package com.example.shupresentation.generic.mvvm

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

object RxCompletableExtension {

    internal fun Completable.subscribeOnIO(): Completable {
        return subscribeOn(Schedulers.io())
    }

    internal fun Completable.observeOnMain(): Completable {
        return observeOn(Schedulers.computation())
    }
}
