package com.example.shudata.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shudata.database.converter.CustomWordConverter
import com.example.shudomain.list.model.CustomListWord

@Entity(tableName = "custom_lists")
@TypeConverters(CustomWordConverter::class)
data class CustomListEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "list_title") val titleList: String,
    @ColumnInfo(name = "list_description") val descriptionList: String,
    @ColumnInfo(name = "list_words") val customWords: List<CustomListWord>,
)
