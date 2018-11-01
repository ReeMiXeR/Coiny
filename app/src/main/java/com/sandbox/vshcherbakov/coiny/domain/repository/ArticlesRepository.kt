package com.sandbox.vshcherbakov.coiny.domain.repository

import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.domain.model.SortOrder

interface ArticlesRepository {
    suspend fun getArticleList(sortOrder: SortOrder, category: String?): List<Article>
}