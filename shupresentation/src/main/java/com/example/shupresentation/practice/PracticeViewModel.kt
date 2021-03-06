package com.example.shupresentation.practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shudomain.list.GetCustomLists
import com.example.shudomain.list.model.CustomList
import com.example.shudomain.practice.GetAllHiragana
import com.example.shudomain.practice.GetAllKatakana
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.generic.mvvm.RxSingleExtension.observeOnMain
import com.example.shupresentation.generic.mvvm.RxSingleExtension.postUIStateTo
import com.example.shupresentation.generic.mvvm.RxSingleExtension.subscribeOnIO
import com.example.shupresentation.generic.mvvm.UIState
import com.example.shupresentation.practice.PracticeNavigationAction.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class PracticeViewModel @Inject constructor(
    private val getAllHiragana: GetAllHiragana,
    private val getAllKatakana: GetAllKatakana,
    private val getCustomLists: GetCustomLists,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<PracticeNavigationAction>()
    val navigation: LiveData<PracticeNavigationAction> = _navigation

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _hiragana = MutableLiveData<List<AlphabetCharacter>>()
    val hiragana: LiveData<List<AlphabetCharacter>> by lazy {
        getHiragana()
        _hiragana
    }

    private val _katakana = MutableLiveData<List<AlphabetCharacter>>()
    val katakana: LiveData<List<AlphabetCharacter>> by lazy {
        _katakana
    }

    private val _customLists = MutableLiveData<List<CustomList>>()
    val customLists: LiveData<List<CustomList>> by lazy {
        _customLists
    }

    private fun getHiragana() {
        getAllHiragana()
            .subscribeOnIO()
            .observeOnMain()
            .postUIStateTo(_uiState)
            .subscribe({
                _hiragana.postValue(it)
                getKatakana()
            }, Timber::e)
            .addTo(compositeDisposable)
    }

    private fun getKatakana() {
        getAllKatakana()
            .subscribeOnIO()
            .observeOnMain()
            .postUIStateTo(_uiState)
            .subscribe({
                _katakana.postValue(it)
                getLists()
            }, Timber::e)
            .addTo(compositeDisposable)
    }

    private fun getLists() {
        getCustomLists()
            .subscribeOnIO()
            .observeOnMain()
            .postUIStateTo(_uiState)
            .subscribe(_customLists::postValue, Timber::e)
            .addTo(compositeDisposable)
    }

    fun openHiragana(character: String) {
        _navigation.postValue(OpenHiragana(character))
    }

    fun openHiraganaPractice(hiragana: String) {
        _navigation.postValue(OpenHiraganaPractice(hiragana))
    }

    fun openKatakana(character: String) {
        _navigation.postValue(OpenKatakana(character))
    }

    fun openKatakanaPractice(katakana: String) {
        _navigation.postValue(OpenKatakanaPractice(katakana))
    }

    fun openAddList() {
        _navigation.postValue(OpenAddList)
    }

    fun openWordList(listId: String) {
        _navigation.postValue(OpenList(listId))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
