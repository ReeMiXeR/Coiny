package com.sandbox.vshcherbakov.coiny.ui.view

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.util.DisplayMetrics


typealias TransitionElements = Array<Pair<View, String>>

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun MenuItem.tag() = this.itemId.toString()

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction, transitionId: Int = FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
    beginTransaction()
            .func()
            .setTransition(transitionId)
//            .setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .addToBackStack(null) // todo ????
            .commit()
    executePendingTransactions()
}

//setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
fun AppCompatActivity.addFragment(fragment: Fragment, containerId: Int, tag: String? = null) {
    supportFragmentManager.inTransaction ({ add(containerId, fragment, tag) })
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, containerId: Int) {
    supportFragmentManager.inTransaction ({ replace(containerId, fragment) })
}

fun AppCompatActivity.hideFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction ({ hide(fragment) }, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
}

fun AppCompatActivity.showFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction ({ show(fragment) })
}

fun Int.dpToPixel(context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}