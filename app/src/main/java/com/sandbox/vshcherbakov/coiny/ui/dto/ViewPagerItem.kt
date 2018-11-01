package com.sandbox.vshcherbakov.coiny.ui.dto

import android.support.v4.app.Fragment

data class ViewPagerItem(
        val fragment: () -> Fragment,
        val title: String
)