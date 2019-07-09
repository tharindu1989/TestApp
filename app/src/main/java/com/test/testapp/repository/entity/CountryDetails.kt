package com.test.testapp.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.test.testapp.repository.db.ListConverter

/**
 * Created By Tharindu on 7/8/2019
 *
 */
@Entity(tableName = "CountryDetailsImpl")
data class CountryDetails(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @TypeConverters(ListConverter::class)
    @ColumnInfo(name = "rows")
    var rows: ArrayList<Detail>? = null
)