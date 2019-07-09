package com.test.testapp.feature.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.testapp.api.entity.Detail
import com.test.testapp.feature.list.viewholder.DetailViewHolder

/**
 * Created By Tharindu on 7/9/2019
 *
 */
class DetailListAdapter : RecyclerView.Adapter<DetailViewHolder>() {

    var countryDetailItems: List<Detail> = arrayListOf()
    var onImageClick: OnImageClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetailViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return countryDetailItems.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        var data = countryDetailItems[position]
        holder.bindData(data)
        holder.onImageClick {
            onImageClick?.onClick(data, position)
        }
    }

    /**
     * refersh Adapter with Data
     * @param items : Items for Adapter
     *
     */
    fun refreshAdapter(items: List<Detail>?) {
        items?.let {
            this.countryDetailItems = it
            notifyDataSetChanged()
        }
    }

    interface OnImageClick {
        fun onClick(data: Detail, index: Int)
    }
}