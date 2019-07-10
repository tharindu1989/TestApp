package com.test.testapp.repository.api

import com.test.testapp.config.AppConfig
import com.test.testapp.repository.entity.CountryDetails
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class ApiClient {

    var countryRepository: com.test.testapp.repository.api.CountryDetailsImpl

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConfig.BaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        countryRepository = retrofit.create(CountryDetailsImpl::class.java)
    }

    /**
     * get Country Details
     */
    fun getCountryDetails(): Observable<CountryDetails> {
        return countryRepository.getCountryDetails()
    }
}