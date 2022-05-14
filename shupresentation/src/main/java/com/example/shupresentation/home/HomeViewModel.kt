package com.example.shupresentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shudomain.exercise.GetHiraganaExercises
import com.example.shudomain.exercise.GetKatakanaExercises
import com.example.shudomain.exercise.GetSteak
import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.model.AlphabetExerciseCharacter
import com.example.shudomain.exercise.model.ExerciseStreak
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.generic.mvvm.RxSingleExtension.observeOnMain
import com.example.shupresentation.generic.mvvm.RxSingleExtension.postUIStateTo
import com.example.shupresentation.generic.mvvm.RxSingleExtension.subscribeOnIO
import com.example.shupresentation.generic.mvvm.UIState
import com.example.shupresentation.home.HomeNavigationAction.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getStreaks: GetSteak,
    private val getHiraganaExercises: GetHiraganaExercises,
    private val getKatakanaExercises: GetKatakanaExercises,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<HomeNavigationAction>()
    val navigation: LiveData<HomeNavigationAction> = _navigation

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _streak = MutableLiveData<List<ExerciseStreak>>()
    val streak: LiveData<List<ExerciseStreak>> by lazy {
        _streak
    }

    private val _hiragana = MutableLiveData<AlphabetExercise>()
    val hiragana: LiveData<AlphabetExercise> by lazy {
        _hiragana
    }

    private val _katakana = MutableLiveData<AlphabetExercise>()
    val katakana: LiveData<AlphabetExercise> by lazy {
        _katakana
    }

    private fun getCurrentStreak() {
        getStreaks()
            .subscribeOnIO()
            .observeOnMain()
            .postUIStateTo(_uiState)
            .subscribe({
                _streak.postValue(it)
                getHiraganaExercise()
            }, Timber::e)
            .addTo(compositeDisposable)
    }

    private fun getHiraganaExercise() {
        getHiraganaExercises()
            .subscribeOnIO()
            .observeOnMain()
            .postUIStateTo(_uiState)
            .subscribe({
                _hiragana.postValue(it)
                getKatakanaExercise()
            }, Timber::e)
            .addTo(compositeDisposable)
    }

    private fun getKatakanaExercise() {
        getKatakanaExercises()
            .subscribeOnIO()
            .observeOnMain()
            .postUIStateTo(_uiState)
            .subscribe(_katakana::postValue, Timber::e)
            .addTo(compositeDisposable)
    }

    fun retrieveData() {
        getCurrentStreak()
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
