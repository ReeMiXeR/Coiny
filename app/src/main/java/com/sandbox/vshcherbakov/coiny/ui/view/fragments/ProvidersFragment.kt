package com.sandbox.vshcherbakov.coiny.ui.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.ui.view.TransitionElements
import com.sandbox.vshcherbakov.coiny.ui.view.activity.ArticleActivity
import com.sandbox.vshcherbakov.coiny.ui.view.activity.ArticleActivity.Companion.ARTICLE_KEY
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegatesManager
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.BaseViewHolder
import com.sandbox.vshcherbakov.coiny.ui.view.delegates.ProviderAdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.dpToPixel
import com.sandbox.vshcherbakov.coiny.ui.view.setGone
import com.sandbox.vshcherbakov.coiny.ui.view.setVisible
import com.sandbox.vshcherbakov.coiny.ui.view_model.ProvidersState
import com.sandbox.vshcherbakov.coiny.ui.view_model.ProvidersViewModel
import kotlinx.android.synthetic.main.fragment_providers.*
import org.jetbrains.anko.intentFor
import org.koin.android.viewmodel.ext.android.viewModel


class ProvidersFragment : BaseFragment() {

    companion object {
        const val TAG = "ProvidersFragment"

        fun newInstance() = ProvidersFragment()
    }

    private val providersViewModel: ProvidersViewModel by viewModel()

    private val adapter = AdapterDelegatesManager(listOf(
            ProviderAdapterDelegate()
    ) as List<AdapterDelegate<BaseViewHolder>>)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_providers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val spanCount = (displayMetrics.widthPixels / 140.dpToPixel(requireContext())).toInt()

        providers_list.layoutManager = GridLayoutManager(requireContext(), spanCount)

        providers_list.adapter = adapter
        providers_list.itemAnimator = CustomItemAnimator()

        subscribeToState()
        subscribeToEvents()

        providersViewModel.loadProvidersList()
    }

    private fun subscribeToEvents() {
        providersViewModel.events.observe(this, Observer { event ->
            event?.let {
                when (it) {
//                    is ProvidersEvent.SwitchToArticle -> switchToArticleActivity(it.transitionElements, it.article)
                }
            }
        })
    }

    private fun subscribeToState() {
        providersViewModel.state.observe(this, Observer { state ->
            state?.let {
                when (it) {
                    is ProvidersState.Loading -> {
                        providers_loading_view.setVisible()
                        providers_list.setGone()
                    }

                    is ProvidersState.Content -> {
                        providers_loading_view.setGone()
                        providers_list.setVisible()
                        adapter.addAll(it.providers)
                    }

                    is ProvidersState.Empty -> TODO()
                    is ProvidersState.Stub -> TODO()
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