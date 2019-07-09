package com.test.testapp.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.testapp.R
import com.test.testapp.feature.BaseFragment
import com.test.testapp.feature.details.adapter.DetailsViewPagerAdapter
import com.test.testapp.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_view_details_layout.*

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class DetailsFragment : BaseFragment() {

    var viewPagerAdapter: DetailsViewPagerAdapter? = null
    var viewModel: DetailsViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_details_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = mActivity?.let {
            ViewModelProviders.of(it).get(DetailsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel?.getCountryDetailList()?.observe(this, Observer {
            viewPagerAdapter?.refresh(it.rows)
        })
        viewModel?.getSelectedPosition()?.observe(this, Observer {
            detailsViewPager?.currentItem = it
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        viewPagerAdapter = DetailsViewPagerAdapter(this.fragmentManager)
        detailsViewPager?.adapter = viewPagerAdapter
    }
}