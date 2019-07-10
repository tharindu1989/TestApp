package com.test.testapp.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.testapp.R
import com.test.testapp.repository.entity.Detail
import com.test.testapp.feature.BaseFragment
import com.test.testapp.feature.details.DetailsFragment
import com.test.testapp.feature.list.adapter.DetailListAdapter
import com.test.testapp.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details_layout.*

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class DetailListFragment : BaseFragment() {

    var viewModel: DetailsViewModel? = null
    private var detailAdapter: DetailListAdapter =
        DetailListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = mActivity?.let {
            ViewModelProviders.of(it).get(DetailsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel?.getCountryDetailList()?.observe(this, Observer {
            detailAdapter.refreshAdapter(it.rows)
            mActivity?.setToolBarTitle(it.title)

        })

        viewModel?.onError?.observe(this, Observer {
            showError(it.message)
        })

        viewModel?.showProgress?.observe(this, Observer {
            if (it) {
                mActivity?.showProgress()
            } else {
                mActivity?.hideProgress()
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialize()

        setListeners()

    }
    private fun initialize() {
        countryDetailsRv.apply {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun setListeners() {
        detailAdapter?.onImageClick = object : DetailListAdapter.OnImageClick {
            override fun onClick(data: Detail, position: Int) {
                viewModel?.selectedPosition?.value = position
                mActivity?.pushFragment(DetailsFragment())
            }
        }

        swipeRefreshLayout?.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel?.getCountryDetails()
        }

        countryDetailsRv?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                var layoutManager = countryDetailsRv.layoutManager as? LinearLayoutManager
                layoutManager?.let {

                    if (isLastItem(it.findLastCompletelyVisibleItemPosition())) {
                        viewModel?.getCountryDetails(true)
                    }
                }
            }
        })
    }

    /**
     * check RecyclerView is at last item
     */
    fun isLastItem(adapterItems: Int?): Boolean {
        return adapterItems == ((viewModel?.getCountryDetailList()?.value?.rows?.size ?: 0) - 1) &&
                viewModel?.showProgress?.value == false
    }

    override fun onResume() {
        super.onResume()
        mActivity?.setToolBarTitle(viewModel?.getCountryDetailList()?.value?.title)
    }
}