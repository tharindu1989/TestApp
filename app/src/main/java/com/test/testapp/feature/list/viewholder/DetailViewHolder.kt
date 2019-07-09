package com.test.testapp.feature.list.viewholder

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.test.testapp.R
import com.test.testapp.api.entity.Detail
import java.lang.Exception

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

    /**
     * bind data to Holder
     * @param detail : Data
     */
    fun bindData(detail: Detail) {
        titleTxt?.text = detail?.title
        descriptionTxt?.text = detail?.description
        Picasso.get()
            .load(detail?.imageHref)
            .error(R.drawable.ic_launcher_background)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .placeholder(R.drawable.ic_launcher_background)
            .into(countryImg, object : Callback {
                override fun onSuccess() {
                    // Not Required
                }

                override fun onError(e: Exception?) {
                    Picasso.get()
                        .load(detail?.imageHref)
                        .error(R.drawable.ic_launcher_background)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(countryImg)

                }
            })
    }

    fun onImageClick(onClick: () -> Unit) {
        countryImg?.setOnClickListener {
            onClick.invoke()
        }
    }
}