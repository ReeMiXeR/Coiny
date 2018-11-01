package com.sandbox.vshcherbakov.coiny.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.FeedPagerAdapter
import com.sandbox.vshcherbakov.coiny.ui.view.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_feed.*


class SettingsFragment : BaseFragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // todo delete?
    }
}
