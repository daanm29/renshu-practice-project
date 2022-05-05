package com.example.shudata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shudata.database.dao.KanaDao
import com.example.shudata.database.entity.Hiragana
import com.example.shudata.database.entity.Katakana

@Database(
    entities = [
        Katakana::class,
        Hiragana::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RenshuDatabase : RoomDatabase() {

    abstract fun kanaDao(): KanaDao

    companion object {

        private const val DATABASE_NAME = "JAP_DATABASE"

        fun getInstance(context: Context): RenshuDatabase {
            return Room.databaseBuilder(context, RenshuDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
