package com.test.testapp.repository

import android.app.Application
import com.test.testapp.repository.api.ApiClient
import com.test.testapp.repository.db.CountryDetailsDataBase
import com.test.testapp.repository.entity.CountryDetails
import com.test.testapp.repository.entity.Detail
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created By Tharindu on 7/10/2019
 *
 */

class CountryRepository(context: Application) {

    var apiClient: ApiClient? = null
    var countyDataBase: CountryDetailsDataBase? = null

    init {
        countyDataBase = CountryDetailsDataBase.getInstance(context)
        apiClient = ApiClient()
    }

    /**
     * get Country Details from API, if Fail get from local DB
     * @param isLoadMore : true -> on Load more Items, then append older items to new
     */
    fun getCountryDetails(
        isLoadMore: Boolean,
        onSuccess: (data: CountryDetails) -> Unit,
        onFailed: (throwable: Throwable) -> Unit
    ) {

        ApiClient().getCountryDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if (isLoadMore) {
                    getCountryDetailsFromDB({
                        var detailItems: ArrayList<Detail>? = it?.rows
                        it?.rows?.let {
                            detailItems?.addAll(it)
                        }
                        it?.rows = detailItems
                        insertItems(it) {
                            onSuccess.invoke(it)
                        }
                    }, {
                        onFailed.invoke(it)
                    })
                } else {
                    insertItems(it) {
                        onSuccess.invoke(it)
                    }
                }

            }, {
                getCountryDetailsFromDB(onSuccess, onFailed)
            })

    }

    /**
     * get Country Details from Local DB
     */
    private fun getCountryDetailsFromDB(
        onSuccess: (data: CountryDetails) -> Unit,
        onFailed: (throwable: Throwable) -> Unit
    ) {
        countyDataBase?.getCountryDto()?.get()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                onSuccess.invoke(it)
            }, {
                onFailed.invoke(it)
            })
    }

    /**
     * Insert Items to Local DB
     */
    private fun insertItems(data: CountryDetails, onSuccess: () -> Unit) {
        Flowable.just(
            countyDataBase?.getCountryDto()?.insert(data)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess.invoke()
            }, {

            })
    }
}