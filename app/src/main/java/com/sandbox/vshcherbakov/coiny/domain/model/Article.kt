package com.sandbox.vshcherbakov.coiny.domain.model

import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterItem
import java.io.Serializable

data class Article(
        val id: String,
        val guid: String,
        val published_on: Long,
        val imageurl: String,
        val title: String,
        val url: String,
        val source: String,
        val body: String,
        val tags: String,
        val categories: String,
        val upvotes: String,
        val downvotes: String,
        val lang: String,
        val source_info: SourceInfo
): AdapterItem, Serializable