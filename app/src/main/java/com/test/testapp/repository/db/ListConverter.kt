package com.test.testapp.repository.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.testapp.repository.entity.Detail

/**
 * Created By Tharindu on 7/9/2019
 *
 */
class ListConverter {
    companion object {
        val gson = Gson()
        @TypeConverter
        @JvmStatic
        fun stringToDetail(data: String): ArrayList<Detail> {
            return data?.let {
                val itemType = object : TypeToken<List<Detail>>() {}.type
                gson.fromJson<ArrayList<Detail>>(it, itemType)
            } ?: run {
                arrayListOf<Detail>()
            }
        }

        @TypeConverter
        @JvmStatic
        fun detailToString(items: ArrayList<Detail>): String {
            return gson.toJson(items)
        }
    }
}