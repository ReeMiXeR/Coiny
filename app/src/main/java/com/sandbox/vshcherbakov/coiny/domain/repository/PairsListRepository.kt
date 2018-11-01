package com.sandbox.vshcherbakov.coiny.domain.repository

import com.sandbox.vshcherbakov.coiny.domain.model.CurrencyPair


interface PairsListRepository {
    suspend fun getProviders(): List<CurrencyPair>
}