package com.example.shudata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.shudata.database.entity.CustomListEntity

@Dao
abstract class CustomListDao {

    @Query("SELECT * FROM custom_lists")
    abstract fun getCustomLists(): List<CustomListEntity>

    @Insert(onConflict = REPLACE)
    abstract fun insertCustomList(customList: CustomListEntity)

    @Query("DELETE FROM custom_lists WHERE list_title = :title")
    abstract fun deleteCustomList(title: String)
}
