package com.test.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.testapp.api.ApiClient
import com.test.testapp.api.entity.CountryDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class DetailsViewModel : BaseViewModel() {

    private val countryDetail: MutableLiveData<CountryDetails> by lazy {
        MutableLiveData<CountryDetails>().also {
            getCountryDetails()
        }
    }

    val selectedPosition = MutableLiveData<Int>()

    /**
     * get Country Details
     */
    fun getCountryDetailList(): LiveData<CountryDetails> {
        return countryDetail
    }

    fun getSelectedPosition(): LiveData<Int> {
        return selectedPosition
    }

    /**
     * get Country Details
     */
    fun getCountryDetails() {
        showProgress.value = true
        ApiClient().getCountryDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showProgress.value = false
                countryDetail.value = it
            }, {
                onError(it)
            })
    }

}