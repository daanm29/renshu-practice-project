package com.example.shupresentation.practice.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shudomain.exercise.GetHiraganaExercises
import com.example.shudomain.exercise.GetKatakanaExercises
import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shupresentation.generic.SingleLiveEvent
import com.example.shupresentation.generic.mvvm.RxSingleExtension.observeOnMain
import com.example.shupresentation.generic.mvvm.RxSingleExtension.postUIStateTo
import com.example.shupresentation.generic.mvvm.RxSingleExtension.subscribeOnIO
import com.example.shupresentation.generic.mvvm.UIState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class ExerciseViewModel @Inject constructor(
    private val exerciseViewModelArguments: ExerciseViewModelArguments,
    private val getAllKatakanaExercises: GetKatakanaExercises,
    private val getAllHiraganaExercises: GetHiraganaExercises,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = SingleLiveEvent<ExerciseNavigationAction>()
    val navigation: LiveData<ExerciseNavigationAction> = _navigation

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    private val _exercises = MutableLiveData<AlphabetExercise>()
    val exercise: LiveData<AlphabetExercise> by lazy {
        getExercises(exerciseViewModelArguments.alphabetType)
        _exercises
    }

    private fun getExercises(alphabet: String) {
        if (alphabet == "hiragana") {
            getAllHiraganaExercises()
                .subscribeOnIO()
                .observeOnMain()
                .postUIStateTo(_uiState)
                .subscribe(_exercises::postValue, Timber::e)
                .addTo(compositeDisposable)
        } else {
            getAllKatakanaExercises()
                .subscribeOnIO()
                .observeOnMain()
                .postUIStateTo(_uiState)
                .subscribe(_exercises::postValue, Timber::e)
                .addTo(compositeDisposable)
        }
    }

    fun openPracticeFragment(exercise: AlphabetExercise) {
        _navigation.postValue(ExerciseNavigationAction.OpenPractice)
        /* TODO: DO STUFF WITH EXERCISE */
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
