package com.sandbox.vshcherbakov.coiny.domain.usecase

import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.domain.model.SortOrder
import com.sandbox.vshcherbakov.coiny.domain.repository.ArticlesRepository

class ArticleListUseCase(private val articlesRepository: ArticlesRepository) : BaseUseCase() {

    suspend fun getArticleList(sortOrder: SortOrder, category: String?): List<Article> {
        return articlesRepository.getArticleList(sortOrder, category)
    }
}
