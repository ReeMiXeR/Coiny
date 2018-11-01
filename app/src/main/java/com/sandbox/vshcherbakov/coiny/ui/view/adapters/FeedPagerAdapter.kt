package com.sandbox.vshcherbakov.coiny.ui.view.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.sandbox.vshcherbakov.coiny.ui.dto.ViewPagerItem


class FeedPagerAdapter(val items: Array<ViewPagerItem>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return items.getOrNull(position)?.fragment?.invoke()
                ?: throw IllegalStateException("items[$position] is null")
    }

    override fun getPageTitle(position: Int): CharSequence {
        return items.getOrNull(position)?.title
                ?: throw IllegalStateException("items[$position] is null")
    }

    override fun getCount() = items.size
}
