package com.sandbox.vshcherbakov.coiny.ui.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.ui.view.addFragment
import com.sandbox.vshcherbakov.coiny.ui.view.fragments.FeedFragment
import com.sandbox.vshcherbakov.coiny.ui.view.fragments.PairsListFragment
import com.sandbox.vshcherbakov.coiny.ui.view.fragments.SettingsFragment
import com.sandbox.vshcherbakov.coiny.ui.view.hideFragment
import com.sandbox.vshcherbakov.coiny.ui.view.showFragment
import com.sandbox.vshcherbakov.coiny.ui.view.tag
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var currentFragmentTag: String? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        supportFragmentManager
                .findFragmentByTag(currentFragmentTag)
                ?.let { currentFragment ->
                    if (!currentFragmentTag.equals(item.tag())) {
                        hideFragment(currentFragment)
                    }
                }

        supportFragmentManager
                .findFragmentByTag(item.tag())
                ?.let { targetFragment ->
                    showFragment(targetFragment)
                } ?: addMenuItemFragment(item)

        currentFragmentTag = item.tag()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            main_navigation_view.selectedItemId = R.id.navigation_feed
            currentFragmentTag = R.id.navigation_feed.toString()
            addFragment(FeedFragment.newInstance(), R.id.main_container, currentFragmentTag)
        }

        main_navigation_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener) // todo мб завернуть в блок savedInstanceState?
    }


    private fun addMenuItemFragment(item: MenuItem) {
        val fragment = when (item.itemId) {
            R.id.navigation_currency -> PairsListFragment.newInstance()
            R.id.navigation_feed -> FeedFragment.newInstance()
            R.id.navigation_settings -> SettingsFragment.newInstance()
            else -> throw IllegalStateException("Unhandled bottom navigation menu item - ${item.title}")
        }
        addFragment(fragment, R.id.main_container, item.tag())
    }
}
