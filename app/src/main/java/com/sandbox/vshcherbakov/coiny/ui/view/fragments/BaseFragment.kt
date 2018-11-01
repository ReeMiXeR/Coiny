package com.sandbox.vshcherbakov.coiny.ui.view.fragments

import android.support.v4.app.Fragment

open class BaseFragment: Fragment() {
    protected fun extractNonNull(key: String): Any {
        return arguments?.get(key) ?: throw IllegalStateException("$key is mull")
    }

    protected fun extract(key: String): Any? {
        return arguments?.get(key)
    }
}