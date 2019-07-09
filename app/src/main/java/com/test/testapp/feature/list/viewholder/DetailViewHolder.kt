package com.test.testapp.feature.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.testapp.R
import com.test.testapp.api.entity.Detail

/**
 * Created By Tharindu on 7/9/2019
 *
 */
class DetailViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_country_detail, parent, false)) {

    private var titleTxt: TextView? = null
    private var descriptionTxt: TextView? = null
    private var countryImg: ImageView? = null

    init {
        titleTxt = itemView.findViewById(R.id.titleTxt)
        descriptionTxt = itemView.findViewById(R.id.descriptionTxt)
        countryImg = itemView.findViewById(R.id.countryImg)
    }

    fun bindData(detail: Detail) {
        titleTxt?.text = detail?.title
        descriptionTxt?.text = detail?.description
    }
}