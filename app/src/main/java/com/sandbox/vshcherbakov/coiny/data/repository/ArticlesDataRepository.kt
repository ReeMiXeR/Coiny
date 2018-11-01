package com.sandbox.vshcherbakov.coiny.data.repository

import com.sandbox.vshcherbakov.coiny.data.network.ApiHolder
import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.domain.model.SortOrder
import com.sandbox.vshcherbakov.coiny.domain.repository.ArticlesRepository

class ArticlesDataRepository(private val apiHolder: ApiHolder) : ArticlesRepository {
    override suspend fun getArticleList(sortOrder: SortOrder, category: String?): List<Article> {
        val request = apiHolder.provide().getArticleList(sortOrder.name.toLowerCase(), category)
        return request.await().Data
    }
}