package com.test.testapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.testapp.api.ApiClient
import com.test.testapp.api.entity.CountryDetails
import com.test.testapp.api.entity.Detail
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

    var isLoadMore: Boolean = false

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
    @SuppressLint("CheckResult")
    fun getCountryDetails(isLoadMore: Boolean = false) {

        showProgress.value = true
        this.isLoadMore = isLoadMore

        ApiClient().getCountryDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showProgress.value = false
                if (isLoadMore) {
                    var detailItems: ArrayList<Detail>? = countryDetail.value?.rows
                    it?.rows?.let {
                        detailItems?.addAll(it)
                    }
                    it?.rows = detailItems
                    countryDetail.value = it
                } else {
                    countryDetail.value = it
                }
            }, {
                onError(it)
            })
    }

}