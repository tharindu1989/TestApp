package com.test.testapp.feature.component

import android.app.Dialog
import android.content.Context
import com.test.testapp.R

/**
 * Created By Tharindu on 7/9/2019
 *
 */
class ProgressDialog : Dialog{

    constructor(context: Context) : super(context, android.R.style.Theme_Translucent_NoTitleBar) {

        this.setContentView(R.layout.dialog_progress_layout)
        this.setCancelable(true)
    }
}