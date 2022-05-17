package com.example.shupresentation.practice.list.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shudomain.list.SaveCustomList
import com.example.shudomain.list.model.CustomList
import com.example.shudomain.list.model.CustomListWord
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.practice.list.add.ListAddNavigationAction.OpenPractice
import com.example.shupresentation.practice.list.add.validator.CustomListForm
import com.example.shupresentation.practice.list.add.validator.CustomListFormField
import com.example.shupresentation.practice.list.add.validator.CustomListFormValidator
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class ListAddViewModel @Inject constructor(
    private val customListFormValidator: CustomListFormValidator,
    private val saveCustomList: SaveCustomList,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<ListAddNavigationAction>()
    val addNavigation: LiveData<ListAddNavigationAction> = _navigation

    private val _invalidFields = MutableLiveData<CustomListFormField?>()
    val invalidFields: LiveData<CustomListFormField?> by lazy {
        _invalidFields
    }

    fun onCreateListClicked(form: CustomListForm, customWords: ArrayList<CustomListWord>) {
        val invalidFields = customListFormValidator.getInvalidFromFields(form)
        if (!invalidFields.isNullOrEmpty()) {
            _invalidFields.postValue(invalidFields[0])
        } else {
            saveCustomList(CustomList(form.title, form.description, customWords))
                .subscribe({
                    _invalidFields.postValue(null)
                    _navigation.postValue(OpenPractice)
                }, Timber::e)
                .addTo(compositeDisposable)
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
