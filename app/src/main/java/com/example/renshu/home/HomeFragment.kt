package com.example.renshu.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import java.time.YearMonth
import java.time.ZoneId

class HomeFragment : DaggerFragment(R.layout.fragment_home) {

    private val ui by viewBinding<FragmentHomeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCurrentMonth()
    }

    @SuppressLint("NewApi")
    private fun setCurrentMonth() {
        val firstDay = YearMonth.now().atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val lastDay = YearMonth.now().atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

        ui.calendarView.apply {
            minDate = firstDay
            maxDate = lastDay
        }
    }
}
