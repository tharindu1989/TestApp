package com.test.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.test.testapp.api.entity.Detail

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class DetailsViewModel : BaseViewModel() {

    var countryDetail = MutableLiveData<List<Detail>>()

}