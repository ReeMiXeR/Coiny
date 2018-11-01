package com.sandbox.vshcherbakov.coiny.data.repository

import com.sandbox.vshcherbakov.coiny.data.network.ApiHolder
import com.sandbox.vshcherbakov.coiny.domain.model.CurrencyPair
import com.sandbox.vshcherbakov.coiny.domain.repository.PairsListRepository

class PairsListDataRepository(private val apiHolder: ApiHolder) : PairsListRepository {
    override suspend fun getProviders(): List<CurrencyPair> {
        val response = apiHolder.provide().getFullData().await()
        return response.DISPLAY.values.map { it.values.first() }
    }
}