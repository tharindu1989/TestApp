package com.test.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.test.testapp.api.ApiClient
import com.test.testapp.api.entity.CountryDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class ListViewModel : BaseViewModel() {

    var countryDetail = MutableLiveData<CountryDetails>()

    /**
     * get Country Details
     */
    fun getCountryDetails() {
        ApiClient().getCountryDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                countryDetail.value = it
            }, {
                onError(it)
            })
    }
}