package com.example.shudata.kana

import java.util.*

object DateExtension {

    fun Date.isDateEqual(other: Date): Boolean {
        return this.year == other.year &&
            this.month == other.month &&
            this.date == other.date
    }

    fun Date.isOtherDateYesterday(other: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = other
        calendar.add(Calendar.DATE, 1)

        return calendar.time.date == this.date
    }
}
