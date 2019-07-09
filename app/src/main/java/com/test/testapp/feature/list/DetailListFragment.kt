package com.test.testapp.feature.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testapp.R
import com.test.testapp.api.entity.Detail
import com.test.testapp.feature.BaseFragment
import com.test.testapp.feature.details.DetailsFragment
import com.test.testapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_details_layout.*

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class DetailListFragment : BaseFragment() {

    var viewModel: ListViewModel? = null
    private var detailAdapter: DetailListAdapter = DetailListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel?.countryDetail?.observe(this, Observer {
            Log.e("DATA", it.title)
            detailAdapter.refreshAdapter(it.rows)
            mActivity?.setToolBarTitle(it.title)
        })

        viewModel?.onError?.observe(this, Observer {
            showError(it.message)
        })

        viewModel?.getCountryDetails()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()

        setListeners()
    }

    private fun initialize() {
        countryDetailsRv.apply {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setListeners() {
        detailAdapter?.onImageClick = object : DetailListAdapter.OnImageClick {
            override fun onClick(data: Detail) {
                var countryList = viewModel?.countryDetail?.value?.rows as? ArrayList
                var bundle = Bundle()
                bundle.putParcelable("selectedItem", data)
                bundle.putParcelableArrayList("itemList", countryList)
                mActivity?.pushFragment(DetailsFragment(), bundle)
            }
        }
    }
}