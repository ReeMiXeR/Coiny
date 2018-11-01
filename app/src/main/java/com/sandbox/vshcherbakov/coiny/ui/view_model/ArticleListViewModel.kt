package com.sandbox.vshcherbakov.coiny.ui.view_model

import android.arch.lifecycle.ViewModel
import com.sandbox.vshcherbakov.coiny.domain.utils.SingleLiveEvent
import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.domain.usecase.ArticleListUseCase
import com.sandbox.vshcherbakov.coiny.ui.dto.ArticleListParams
import com.sandbox.vshcherbakov.coiny.ui.view.TransitionElements
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class ArticleListViewModel(private val articleListUseCase: ArticleListUseCase) : ViewModel() {

    val state = SingleLiveEvent<ArticleListState>()
    val events = SingleLiveEvent<ArticleListEvent>()

    init {
        state.postValue(ArticleListState.Loading)
    }

    fun loadArticleList(params: ArticleListParams) = runBlocking(Dispatchers.Default) {
        val data = articleListUseCase.getArticleList(params.sortOrder, params.category)
        state.postValue(ArticleListState.Content(data))
    }

    fun onCategoryItemClick(transitionElements: TransitionElements, article: Article) {
        val event = ArticleListEvent.SwitchToArticle(transitionElements, article)
        events.postValue(event)
    }
}

sealed class ArticleListEvent {
    data class SwitchToArticle(
            val transitionElements: TransitionElements,
            val article: Article
    ) : ArticleListEvent()
}

sealed class ArticleListState {
    object Loading : ArticleListState()
    object Empty : ArticleListState()
    object Stub : ArticleListState()
    data class Content(val articles: List<Article>) : ArticleListState()
}
