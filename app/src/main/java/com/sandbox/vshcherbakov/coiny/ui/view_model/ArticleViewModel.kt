package com.sandbox.vshcherbakov.coiny.ui.view_model

import android.arch.lifecycle.ViewModel
import com.sandbox.vshcherbakov.coiny.domain.utils.SingleLiveEvent

class ArticleViewModel : ViewModel() {
    val events = SingleLiveEvent<ArticleEvent>()

    fun onArticleItemClick(category: String) {
        val event = ArticleEvent.SwitchToCategoryList(category)
        events.value = event
    }
}

sealed class ArticleEvent {
    data class SwitchToCategoryList(val category: String) : ArticleEvent()
}