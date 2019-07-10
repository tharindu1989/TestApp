package com.test.testapp.repository.api

import com.test.testapp.repository.entity.CountryDetails
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created By Tharindu on 7/8/2019
 *
 */
interface CountryDetailsImpl {

    @GET("facts.json")
    fun getCountryDetails(): Observable<CountryDetails>
}