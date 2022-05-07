package com.example.renshu.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentHomeBinding
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

    /* TODO: DO THE THINGS WITH THE VIEWMODEL */
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private val ui by viewBinding<FragmentHomeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        setCurrentMonth()
    }

    private fun setCurrentMonth() {
        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
        val installDate = simpleDateFormat.parse(sharedPreferences.getString("install_date", "").toString())
            ?: simpleDateFormat.parse(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime().toString())

        val calendarDay = CalendarDay.from(installDate.year + YEAR, installDate.month + MONTH, installDate.day)

        ui.calendarView.state().edit()
            .setMinimumDate(calendarDay)
            .commit()
    }

    companion object {

        private const val YEAR = 1900

        private const val MONTH = 1
    }
}
