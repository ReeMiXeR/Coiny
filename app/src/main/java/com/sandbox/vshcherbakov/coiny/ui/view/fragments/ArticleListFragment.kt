package com.sandbox.vshcherbakov.coiny.ui.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.domain.model.SortOrder
import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.ui.dto.ArticleListParams
import com.sandbox.vshcherbakov.coiny.ui.view.TransitionElements
import com.sandbox.vshcherbakov.coiny.ui.view.activity.ArticleActivity
import com.sandbox.vshcherbakov.coiny.ui.view.activity.ArticleActivity.Companion.ARTICLE_KEY
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegatesManager
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.BaseViewHolder
import com.sandbox.vshcherbakov.coiny.ui.view.delegates.ArticleAdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.delegates.TitleAdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.setGone
import com.sandbox.vshcherbakov.coiny.ui.view.setVisible
import com.sandbox.vshcherbakov.coiny.ui.view_model.ArticleListEvent
import com.sandbox.vshcherbakov.coiny.ui.view_model.ArticleListState
import com.sandbox.vshcherbakov.coiny.ui.view_model.ArticleListViewModel
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.jetbrains.anko.intentFor
import org.koin.android.viewmodel.ext.android.viewModel


class ArticleListFragment : BaseFragment() {

    companion object {
        const val TAG = "ArticleListFragment"

        private const val KEY_BUNDLE_ARTICLE_LIST_PARAMS = "$TAG.article_list_params"

        fun newInstance(params: ArticleListParams): ArticleListFragment {
            return ArticleListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_BUNDLE_ARTICLE_LIST_PARAMS, params)
                }
            }
        }
    }

    private val params by lazy { extract(KEY_BUNDLE_ARTICLE_LIST_PARAMS) as ArticleListParams }

    private val articleListViewModel: ArticleListViewModel by viewModel()

    private val adapter = AdapterDelegatesManager(listOf(
            ArticleAdapterDelegate { transitionElements, article -> articleListViewModel.onCategoryItemClick(transitionElements, article) },
            TitleAdapterDelegate()
    ) as List<AdapterDelegate<BaseViewHolder>>)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        articles_list.layoutManager = LinearLayoutManager(requireContext())
        articles_list.adapter = adapter
        articles_list.itemAnimator = CustomItemAnimator()

        subscribeToState()
        subscribeToEvents()

        articleListViewModel.loadArticleList(params)
    }

    private fun subscribeToEvents() {
        articleListViewModel.events.observe(this, Observer { event ->
            event?.let {
                when (it) {
                    is ArticleListEvent.SwitchToArticle -> switchToArticleActivity(it.transitionElements, it.article)
                }
            }
        })
    }

    private fun subscribeToState() {
        articleListViewModel.state.observe(this, Observer { state ->
            state?.let {
                when (it) {
                    is ArticleListState.Loading -> {
                        providers_loading_view.setVisible()
                        articles_list.setGone()
                    }

                    is ArticleListState.Content -> {
                        providers_loading_view.setGone()
                        articles_list.setVisible()
                        adapter.addAll(it.articles)
                    }

                    is ArticleListState.Empty -> TODO()
                    is ArticleListState.Stub -> TODO()
                }

            }
        })
    }

    private fun switchToArticleActivity(transitionElements: TransitionElements, article: Article) {
        val intent = requireActivity().intentFor<ArticleActivity>(ARTICLE_KEY to article)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), *transitionElements) // oh yeah
        startActivity(intent, options.toBundle())
    }

}

class CustomItemAnimator : SimpleItemAnimator() {
    override fun recordPreLayoutInformation(state: RecyclerView.State, viewHolder: RecyclerView.ViewHolder, changeFlags: Int, payloads: MutableList<Any>): ItemHolderInfo {
        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads)
    }

    override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
        return true
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {
        val anim = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f)
        anim.duration = 500
        holder.itemView.startAnimation(anim)
        return true
    }

    override fun animateMove(holder: RecyclerView.ViewHolder, fromX: Int, fromY: Int, toX: Int, toY: Int): Boolean {
        val anim = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f)
        anim.duration = 500
        holder.itemView.startAnimation(anim)
        return false
    }

    override fun animateChange(oldHolder: RecyclerView.ViewHolder, newHolder: RecyclerView.ViewHolder, fromLeft: Int, fromTop: Int, toLeft: Int, toTop: Int): Boolean {
        return false
    }

    override fun runPendingAnimations() {
        Log.e("", "")
    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {
        Log.e("", "")
    }

    override fun endAnimations() {
        Log.e("", "")
    }

    override fun isRunning(): Boolean {
        return false
    }
}
