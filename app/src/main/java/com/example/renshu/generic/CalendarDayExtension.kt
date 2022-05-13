package com.example.renshu.generic

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

object CalendarDayExtension {

    fun Date.toCalendarDay() : CalendarDay {
        return CalendarDay.from(
            this.year + 1900,
            this.month + 1,
            this.day + 8
        )
    }
}
