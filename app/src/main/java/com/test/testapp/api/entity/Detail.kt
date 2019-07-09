package com.test.testapp.api.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created By Tharindu on 7/8/2019
 *
 */
@Parcelize
data class Detail(
    val title: String? = null,
    val description: String? = null,
    val imageHref: String? = null
) : Parcelable