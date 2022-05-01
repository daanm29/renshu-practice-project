package com.example.renshu.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

/**
 * A [ViewModelProvider.Factory] that can host multiple [ViewModel]s
 * This is concept is mostly based on this user's comment from stack overflow.
 * [Found at](https://stackoverflow.com/a/48837581)
 */
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown model class $modelClass")
        @Suppress("UNCHECKED_CAST")
        return creator.get() as T
    }
}
