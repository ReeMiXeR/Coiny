package com.sandbox.vshcherbakov.coiny.data.network

import com.sandbox.vshcherbakov.coiny.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiHolder {
    companion object {
        const val BASE_URL = "https://min-api.cryptocompare.com/"
    }

    private val api: Api

    init {

        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(Api::class.java)
    }

    fun provide(): Api = api
}