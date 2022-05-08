package com.example.shupresentation.practice.alphabet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shudomain.practice.GetAllHiragana
import com.example.shudomain.practice.GetAllKatakana
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.generic.mvvm.RxSingleExtension.observeOnMain
import com.example.shupresentation.generic.mvvm.RxSingleExtension.postUIStateTo
import com.example.shupresentation.generic.mvvm.RxSingleExtension.subscribeOnIO
import com.example.shupresentation.generic.mvvm.UIState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class AlphabetViewModel @Inject constructor(
    private val alphabetViewModelArguments: AlphabetViewModelArguments,
    private val getAllHiragana: GetAllHiragana,
    private val getAllKatakana: GetAllKatakana,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<AlphabetNavigationAction>()
    val navigation: LiveData<AlphabetNavigationAction> = _navigation

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _alphabet = MutableLiveData<List<AlphabetCharacter>>()
    val alphabet: LiveData<List<AlphabetCharacter>> by lazy {
        getAlphabet(alphabetViewModelArguments.alphabetType)
        _alphabet
    }

    private fun getAlphabet(alphabet: String) {
        if (alphabet.lowercase() == "hiragana") {
            getAllHiragana()
                .subscribeOnIO()
                .observeOnMain()
                .postUIStateTo(_uiState)
                .subscribe(_alphabet::postValue, Timber::e)
                .addTo(compositeDisposable)
        } else {
            getAllKatakana()
                .subscribeOnIO()
                .observeOnMain()
                .postUIStateTo(_uiState)
                .subscribe(_alphabet::postValue, Timber::e)
                .addTo(compositeDisposable)
        }
    }

    fun openOtherCharacter(character: String) {
        _navigation.postValue(AlphabetNavigationAction.OpenOtherCharacter(character))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
