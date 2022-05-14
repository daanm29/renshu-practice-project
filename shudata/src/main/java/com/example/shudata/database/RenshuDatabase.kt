package com.example.shudata.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.shudata.database.dao.HiraganaDao
import com.example.shudata.database.dao.KatakanaDao
import com.example.shudata.database.dao.StreakDao
import com.example.shudata.database.entity.*

@Database(
    entities = [
        HiraganaEntity::class,
        HiraganaProgressEntity::class,
        KatakanaEntity::class,
        KatakanaProgressEntity::class,
        StreakEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
abstract class RenshuDatabase : RoomDatabase() {

    abstract fun hiraganaDao(): HiraganaDao

    abstract fun katakanaDao(): KatakanaDao

    abstract fun streakDao(): StreakDao

    companion object {

        private const val DATABASE_NAME = "RENSHU_DATABASE"

        fun getInstance(context: Context): RenshuDatabase {
            return Room.databaseBuilder(context, RenshuDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
