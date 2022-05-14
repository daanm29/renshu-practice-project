package com.example.renshu.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentHomeBinding
import com.example.renshu.generic.CalendarDayExtension.toCalendarDay
import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shudomain.exercise.model.AlphabetExerciseCharacter
import com.example.shudomain.exercise.model.ExerciseStreak
import com.example.shupresentation.generic.mvvm.UIState
import com.example.shupresentation.home.HomeNavigationAction
import com.example.shupresentation.home.HomeViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.android.support.DaggerFragment
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HomeFragment : DaggerFragment(R.layout.fragment_home) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var sharedPreferences: SharedPreferences

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private val ui by viewBinding<FragmentHomeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE)

        setCurrentMonth()
        observeViewModel()
    }

    private fun setCurrentMonth() {
        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
        val installDate = simpleDateFormat.parse(sharedPreferences.getString("install_date", "").toString())
            ?: simpleDateFormat.parse(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime().toString())

        val calendarDay = CalendarDay.from(installDate.year + 1900, installDate.month + 1, installDate.day)

        ui.calendarView.state().edit()
            .setMinimumDate(calendarDay)
            .commit()
    }

    private fun observeViewModel() {
        viewModel.retrieveData()

        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigation)
        viewModel.uiState.observe(viewLifecycleOwner, ::handleUIState)
        viewModel.streak.observe(viewLifecycleOwner, ::handleStreak)
        viewModel.hiragana.observe(viewLifecycleOwner, ::handleHiragana)
        viewModel.katakana.observe(viewLifecycleOwner, ::handleKatakana)
    }

    private fun handleNavigation(action: HomeNavigationAction) {
        when (action) {
            HomeNavigationAction.OpenHiraganaPractice -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToKanaExerciseFragment()
                        .setAlphabet(getString(R.string.alphabet_hiragana).lowercase())
                )

            }
            HomeNavigationAction.OpenRecentKatakana -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToKanaExerciseFragment()
                        .setAlphabet(getString(R.string.alphabet_katakana).lowercase())
                )
            }
            HomeNavigationAction.OpenRecentListPractice -> {}
        }
    }

    private fun handleUIState(uiState: UIState) {
        when (uiState) {
            UIState.NORMAL -> {}
            UIState.LOADING -> {}
            UIState.ERROR -> {}
        }
    }

    private fun handleStreak(streaks: List<ExerciseStreak>) {
        if (!streaks.isNullOrEmpty()) {
            val bestStreak = getBestStreak(streaks)
            val lastStudied = getLastStudied(streaks[0])

            ui.streakLayout.bestStreak = bestStreak
            ui.streakLayout.lastStudied = lastStudied

            for (streak in streaks) {
                getStreakDays(streak).forEach { ui.calendarView.setDateSelected(it.toCalendarDay(), true) }

                if (streak.currentDate.date == Date().date) {
                    ui.streakLayout.currentStreak = streak.streakLength
                }
            }
        }
    }

    private fun getBestStreak(streaks: List<ExerciseStreak>): Int {
        var highestStreak = 0

        for (streak in streaks) {
            if (streak.streakLength > highestStreak) {
                highestStreak = streak.streakLength
            }
        }

        return highestStreak
    }

    private fun getLastStudied(streak: ExerciseStreak): Int {
        return (((((Date().time - streak.currentDate.time) / 1000) / 60) / 60) / 24).toInt()
    }

    private fun getStreakDays(streak: ExerciseStreak): List<Date> {
        val dateList = arrayListOf<Date>()
        dateList.add(streak.startDate)

        val calendar = Calendar.getInstance()
        calendar.time = streak.startDate

        while (calendar.time.before(streak.currentDate)) {
            dateList.add(calendar.time)
            calendar.add(Calendar.DATE, 1)
        }

        if (streak.startDate != streak.currentDate) {
            dateList.add(streak.currentDate)
        }

        return dateList
    }

    private fun handleHiragana(hiraganaProgress: AlphabetExercise) {
        ui.hiraganaProgressBar.apply {
            totalProgress = hiraganaProgress.exercisesTodo.size + hiraganaProgress.exercisesDone.size
            currentProgress = hiraganaProgress.exercisesDone.size
        }

        ui.hiraganaRatingBar.rating = hiraganaProgress.mastered.toFloat() / 2

        if (ui.hiraganaProgressBar.currentProgress > 0 || hiraganaProgress.mastered > 0) {
            ui.hiraganaProgressBar.buttonEnabled = true
            ui.hiraganaProgressBar.onButtonClicked?.setOnClickListener {
                viewModel.openHiraganaLastPractice()
            }
        }
    }

    private fun handleKatakana(katakanaProgress: AlphabetExercise) {
        ui.katakanaProgressBar.apply {
            totalProgress = katakanaProgress.exercisesTodo.size + katakanaProgress.exercisesDone.size
            currentProgress = katakanaProgress.exercisesDone.size
        }

        ui.katakanaRatingBar.rating = katakanaProgress.mastered.toFloat() / 2

        if (ui.katakanaProgressBar.currentProgress > 0 || katakanaProgress.mastered > 0) {
            ui.katakanaProgressBar.buttonEnabled = true
            ui.katakanaProgressBar.onButtonClicked?.setOnClickListener {
                viewModel.openKatakanaLastPractice()
            }
        }
    }
}
