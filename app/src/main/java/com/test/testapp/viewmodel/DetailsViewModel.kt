package com.test.testapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.testapp.repository.CountryRepository
import com.test.testapp.repository.entity.CountryDetails

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class DetailsViewModel : BaseViewModel {

    constructor(application: Application) : super(application)

    private val countryDetail: MutableLiveData<CountryDetails> by lazy {
        MutableLiveData<CountryDetails>().also {
            getCountryDetails()
        }
    }

    val selectedPosition = MutableLiveData<Int>()

    var isLoadMore: Boolean = false

    var countryRepository: CountryRepository = CountryRepository(getApplication())

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

        countryRepository.getCountryDetails(
            isLoadMore = isLoadMore,
            onSuccess = {
                showProgress.value = false
                countryDetail.value = it
            },
            onFailed = {
                onError(it)
            }
        )

        /*ApiClient().getCountryDetails()
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
            })*/
    }

}