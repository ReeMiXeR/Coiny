package com.sandbox.vshcherbakov.coiny.ui.view.delegates

import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.ui.view.TransitionElements
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterItem
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_article.view.*
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapterDelegate(
        private val onArticleClicked: (TransitionElements, Article) -> Unit
) : AdapterDelegate<ArticleViewHolder>() {
    override fun isItemDelegate(item: AdapterItem) = item is Article

    override fun getLayoutId() = R.layout.list_item_article

    override fun onCreateViewHolder(parent: ViewGroup): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(), null)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(vh: ArticleViewHolder, item: AdapterItem, payload: List<Any>) {
        item as Article // вынести в базовый адаптер через <T>
        vh.bindViewHolder(item, onArticleClicked)
    }
}

class ArticleViewHolder(
        private val view: View
) : BaseViewHolder(view) {
    private val title: TextView = view.article_title
    private val background: ImageView = view.article_background
    private val body: TextView = view.article_body
    private val sourceName: TextView = view.article_source_name
    private val sourceImage: ImageView = view.article_source_image
    private val publishTime: TextView = view.article_publish_time


    fun bindViewHolder(item: Article, onArticleClicked: (TransitionElements, Article) -> Unit) {
        title.text = item.title
        body.text = item.body
        sourceName.text = item.source_info.name

        view.layoutParams = ViewGroup.LayoutParams(-1, -2) // todo выпилить костыль вьюшек адаптера

        view.setOnClickListener {
            val transitionElements = arrayOf(
                    Pair.create<View, String>(title, ViewCompat.getTransitionName(title)),
                    Pair.create<View, String>(background, ViewCompat.getTransitionName(background)),
                    Pair.create<View, String>(body, ViewCompat.getTransitionName(body)),
                    Pair.create<View, String>(sourceImage, ViewCompat.getTransitionName(sourceImage)),
                    Pair.create<View, String>(sourceName, ViewCompat.getTransitionName(sourceName)),
                    Pair.create<View, String>(publishTime, ViewCompat.getTransitionName(publishTime))
            )
            onArticleClicked.invoke(transitionElements, item)
        }

        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        publishTime.text = formatter.format(Date(item.published_on))

        Glide.with(view.context)
                .load(item.imageurl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(background)

        Glide.with(view.context)
                .load(item.source_info.img)
                .apply(RequestOptions.circleCropTransform())
                .into(sourceImage)
    }
}