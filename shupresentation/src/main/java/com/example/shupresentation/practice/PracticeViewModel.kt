package com.example.shupresentation.practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shudomain.practice.GetHiragana
import com.example.shudomain.practice.GetKatakana
import com.example.shudomain.practice.model.AlphabetPortionData
import com.example.shudomain.practice.model.AlphabetType
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PracticeViewModel @Inject constructor(
    private val getHiragana: GetHiragana,
    private val getKatakana: GetKatakana,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _hiragana = MutableLiveData<List<AlphabetPortionData>>()
    val hiragana: LiveData<List<AlphabetPortionData>> by lazy {
        getHiragana(AlphabetType.HIRAGANA)
        _hiragana
    }

    private val _katakana = MutableLiveData<List<AlphabetPortionData>>()
    val katakana: LiveData<List<AlphabetPortionData>> by lazy {
        getKatakana(AlphabetType.KATAKANA)
        _katakana
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
