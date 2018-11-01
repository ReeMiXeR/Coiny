package com.sandbox.vshcherbakov.coiny.data.network

import com.sandbox.vshcherbakov.coiny.data.model.NWCurrencyResponse
import com.sandbox.vshcherbakov.coiny.data.model.NWResponse
import com.sandbox.vshcherbakov.coiny.domain.model.Article
import com.sandbox.vshcherbakov.coiny.domain.model.Provider
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("data/v2/news/")
    fun getArticleList(
            @Query("sortOrder") sortOrder: String,
            @Query("category") category: String?,
            @Query("lang") lang: String = "EN",
            @Query("extraParams") appName: String = "Coiny"
    ): Deferred<NWResponse<Article>>

    @GET("data/news/feeds")
    fun getProviders(): Deferred<List<Provider>>

    @GET("data/pricemultifull")
    fun getFullData(
            @Query("fsyms") from: String = "BTC,ETH,ADA",
            @Query("tsyms") to: String = "USD,ETH,ADA"
    ): Deferred<NWCurrencyResponse>
}