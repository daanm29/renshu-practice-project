package com.example.shupresentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shudomain.exercise.GetHiraganaExercises
import com.example.shudomain.exercise.GetKatakanaExercises
import com.example.shudomain.exercise.GetSteak
import com.example.shudomain.exercise.model.ExerciseStreak
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.generic.mvvm.RxSingleExtension.postUIStateTo
import com.example.shupresentation.generic.mvvm.UIState
import com.example.shupresentation.home.HomeNavigationAction.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getStreak: GetSteak,
    private val getHiraganaExercises: GetHiraganaExercises,
    private val getKatakanaExercises: GetKatakanaExercises,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<HomeNavigationAction>()
    val navigation: LiveData<HomeNavigationAction> = _navigation

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _streak = MutableLiveData<ExerciseStreak>()
    val streak: LiveData<ExerciseStreak> by lazy {
        getCurrentStreak()
        _streak
    }

    private val _hiragana = MutableLiveData<List<AlphabetCharacter>>()
    val hiragana: LiveData<List<AlphabetCharacter>> by lazy {
        getHiraganaExercise()
        _hiragana
    }

    private val _katakana = MutableLiveData<List<AlphabetCharacter>>()
    val katakana: LiveData<List<AlphabetCharacter>> by lazy {
        getKatakanaExercise()
        _katakana
    }

    private fun getCurrentStreak() {
        getStreak()
            .postUIStateTo(_uiState)
            .subscribe(_streak::postValue, Timber::e)
            .addTo(compositeDisposable)
    }

    private fun getHiraganaExercise() {
        getHiraganaExercises()
            .postUIStateTo(_uiState)
            .subscribe()
            .addTo(compositeDisposable)
    }

    private fun getKatakanaExercise() {
        getKatakanaExercises()
            .postUIStateTo(_uiState)
            .subscribe()
            .addTo(compositeDisposable)
    }

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
