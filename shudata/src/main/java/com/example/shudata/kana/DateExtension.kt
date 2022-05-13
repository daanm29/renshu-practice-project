package com.example.shudata.kana

import java.util.*

object DateExtension {

    fun Date.isDateEqual(other: Date): Boolean {
        return this.year == other.year &&
            this.month == other.month &&
            this.date == other.date
    }

    fun Date.isDateEqualYesterday(other:Date): Boolean {
        return other.date.plus(1) == this.date
    }
}
