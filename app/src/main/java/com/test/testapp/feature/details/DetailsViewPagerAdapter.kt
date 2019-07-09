package com.test.testapp.feature.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.test.testapp.api.entity.Detail

/**
 * Created By Tharindu on 7/9/2019
 *
 */
class DetailsViewPagerAdapter(supportFragmentManager: FragmentManager?) :
    FragmentStatePagerAdapter(supportFragmentManager) {

    var detailsItems: List<Detail> = arrayListOf()

    override fun getItem(position: Int): Fragment {
        var data = detailsItems[position]
        return ViewFragment.newInstance(data.imageHref)
    }

    override fun getCount(): Int {
        return detailsItems.size
    }

    fun refresh(items: List<Detail>) {
        this.detailsItems = items
        notifyDataSetChanged()
    }
}