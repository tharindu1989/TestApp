package com.test.testapp.feature.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.testapp.R
import com.test.testapp.feature.BaseFragment
import com.test.testapp.viewmodel.ListViewModel

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class ListFragment : BaseFragment() {

    var viewModel: ListViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel?.countryDetail?.observe(this, Observer {
            Log.e("DATA", it.title)
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
    }
}