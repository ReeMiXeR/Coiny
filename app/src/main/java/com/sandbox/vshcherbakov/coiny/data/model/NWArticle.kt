package com.sandbox.vshcherbakov.coiny.data.model


data class NWArticle(
        val id: String?,
        val guid: String?,
        val published_on: Long?,
        val imageurl: String?,
        val title: String?,
        val url: String?,
        val source: String?,
        val body: String?,
        val tags: String?,
        val categories: String?,
        val upvotes: String?,
        val downvotes: String?,
        val lang: String?,
        val source_info: NWSourceInfo?
)