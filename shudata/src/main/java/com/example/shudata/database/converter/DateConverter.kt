package com.example.shudata.database.converter

import androidx.room.TypeConverter
import java.util.*

object DateConverter {

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(dateLong: Long): Date {
        return Date(dateLong)
    }
}
