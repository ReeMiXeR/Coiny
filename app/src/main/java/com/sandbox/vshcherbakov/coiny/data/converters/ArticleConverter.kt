package com.sandbox.vshcherbakov.coiny.data.converters

import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.data.model.NWArticle

fun convertArticle(article: NWArticle?): Article {
    with(requireNotNull(article)) {
        return Article(
                requireNotNull(id),
                requireNotNull(guid),
                requireNotNull(published_on),
                requireNotNull(imageurl),
                requireNotNull(title),
                requireNotNull(url),
                requireNotNull(source),
                requireNotNull(body),
                requireNotNull(tags),
                requireNotNull(categories),
                requireNotNull(upvotes),
                requireNotNull(downvotes),
                requireNotNull(lang),
                convertSourceInfo(source_info)
        )
    }
}