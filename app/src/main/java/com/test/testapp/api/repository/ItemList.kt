package com.test.testapp.api.repository

import com.test.testapp.api.entity.CountryDetails
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created By Tharindu on 7/8/2019
 *
 */
interface CountryRepository {

    @GET("facts.json")
    fun getCountryDetails(): Observable<CountryDetails>
}