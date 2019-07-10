package com.test.testapp.repository.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.test.testapp.repository.entity.CountryDetails
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created By Tharindu on 7/10/2019
 *
 */
@Dao
interface CountryDetailsDao {

    @Query("SELECT * FROM CountryDetailsImpl")
    fun get(): Single<CountryDetails>

    @Insert(onConflict = REPLACE)
    fun insert(countryDetails: CountryDetails): Long

    @Query("DELETE FROM CountryDetailsImpl")
    fun deleteAll(): Int
}