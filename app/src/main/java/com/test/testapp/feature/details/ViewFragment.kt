package com.test.testapp.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.test.testapp.R
import com.test.testapp.feature.BaseFragment
import kotlinx.android.synthetic.main.fragment_view_layout.*

/**
 * Created By Tharindu on 7/8/2019
 *
 */
class ViewFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var imageUrl = arguments?.getString("image")
        Picasso.get()
            .load(imageUrl)
            .error(R.drawable.ic_image_black_24dp)
            .placeholder(R.drawable.ic_image_black_24dp)
            .into(viewImg)
    }

    companion object {
        fun newInstance(image: String?): Fragment {
            val fragment = ViewFragment()
            val args = Bundle()
            args.putString("image", image)
            fragment.arguments = args
            return fragment
        }
    }
}