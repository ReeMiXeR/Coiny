package com.sandbox.vshcherbakov.coiny.ui.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.ui.dto.ArticleListParams
import com.sandbox.vshcherbakov.coiny.ui.view.addFragment
import com.sandbox.vshcherbakov.coiny.ui.view.fragments.ArticleListFragment


class CategoryListActivity : BaseActivity() {

    companion object {
        const val TAG = "CategoryListActivity"
        const val CATEGORY_KEY = "$TAG.category_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        val category = intent.getStringExtra(CATEGORY_KEY)
                ?: throw IllegalStateException("$CATEGORY_KEY in $TAG is null")

        val params = ArticleListParams(category = category)
        addFragment(ArticleListFragment.newInstance(params), R.id.category_container)

        overridePendingTransition(
                FragmentTransaction.TRANSIT_FRAGMENT_FADE,
                FragmentTransaction.TRANSIT_FRAGMENT_CLOSE // todo разобраться с анимацией
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
