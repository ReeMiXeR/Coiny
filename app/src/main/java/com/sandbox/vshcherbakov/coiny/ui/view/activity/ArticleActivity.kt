package com.sandbox.vshcherbakov.coiny.ui.view.activity

import android.os.Bundle
import android.support.design.chip.Chip
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.ui.view.activity.CategoryListActivity.Companion.CATEGORY_KEY
import com.sandbox.vshcherbakov.coiny.ui.view_model.ArticleEvent
import com.sandbox.vshcherbakov.coiny.ui.view_model.ArticleViewModel
import kotlinx.android.synthetic.main.activity_article.*
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ArticleActivity : BaseActivity() {

    companion object {
        const val TAG = "ArticleActivity"
        const val ARTICLE_KEY = "article_key"
    }

    private val articleViewModel: ArticleViewModel by viewModel()

    private val article by lazy {
        intent.getSerializableExtra(ARTICLE_KEY) as? Article
                ?: throw IllegalStateException("$ARTICLE_KEY in $TAG is null")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        initView(article)
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        articleViewModel.events.observe(this, android.arch.lifecycle.Observer { event ->
            event?.let {
                when (it) {
                    is ArticleEvent.SwitchToCategoryList -> {
                        startActivity<CategoryListActivity>(CATEGORY_KEY to it.category)
                    }
                }
            }
        })
    }

    private fun initView(item: Article) {
        val categories = item.categories.split("|")
        categories.forEach { category ->
            val categoryView = Chip(this)
            categoryView.text = category
            article_categories_view.addView(categoryView)
            categoryView.setOnClickListener { articleViewModel.onArticleItemClick(category) }
        }

        article_title.text = item.title
        article_body.text = item.body
        article_source_name.text = item.source_info.name

        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        article_publish_time.text = formatter.format(Date(item.published_on.toLong()))

        Glide.with(this)
                .load(item.imageurl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(article_background)

        Glide.with(this)
                .load(item.source_info.img)
                .apply(RequestOptions.circleCropTransform())
                .into(article_source_image)
    }
}
