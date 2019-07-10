package com.test.testapp.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.testapp.repository.entity.CountryDetails

/**
 * Created By Tharindu on 7/9/2019
 *
 */
@Database(entities = [CountryDetails::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class CountryDetailsDataBase : RoomDatabase() {
    abstract fun getCountryDto(): CountryDetailsDao

    companion object {
        private var INSTANCE: CountryDetailsDataBase? = null

        fun getInstance(context: Context): CountryDetailsDataBase? {
            if (INSTANCE == null) {
                synchronized(CountryDetailsDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CountryDetailsDataBase::class.java, "country_details.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}