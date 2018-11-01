package com.sandbox.vshcherbakov.coiny.domain.repository

import com.sandbox.vshcherbakov.coiny.domain.model.Provider
import kotlinx.coroutines.Deferred


interface ProvidersRepository {
    fun getProviders(): Deferred<List<Provider>>
}