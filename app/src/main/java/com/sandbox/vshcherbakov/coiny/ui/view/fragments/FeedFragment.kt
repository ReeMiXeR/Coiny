package com.sandbox.vshcherbakov.coiny.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.domain.model.SortOrder
import com.sandbox.vshcherbakov.coiny.ui.dto.ArticleListParams
import com.sandbox.vshcherbakov.coiny.ui.dto.ViewPagerItem
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.FeedPagerAdapter
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : BaseFragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        feed_view_pager.adapter = FeedPagerAdapter(getViewPagerItems(), childFragmentManager)
        feed_tab.setupWithViewPager(feed_view_pager)
    }

    private fun getViewPagerItems(): Array<ViewPagerItem> = arrayOf(
            ViewPagerItem(
                    fragment = { ArticleListFragment.newInstance(ArticleListParams(SortOrder.LATEST)) },
                    title = getString(R.string.feed_tab_latest_title)
            ),
            ViewPagerItem(
                    fragment = { ArticleListFragment.newInstance(ArticleListParams(SortOrder.POPULAR)) },
                    title = getString(R.string.feed_tab_latest_popular)
            ),
            ViewPagerItem(
                    fragment = { ProvidersFragment.newInstance() },
                    title = getString(R.string.feed_tab_latest_providers)
            )
    )
}
