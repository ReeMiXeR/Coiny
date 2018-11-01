package com.sandbox.vshcherbakov.coiny.ui.dto

import com.sandbox.vshcherbakov.coiny.domain.model.SortOrder
import java.io.Serializable

data class ArticleListParams(
        val sortOrder: SortOrder = SortOrder.LATEST,
        val category: String? = null
): Serializable