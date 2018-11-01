package com.sandbox.vshcherbakov.coiny.data.repository

import com.sandbox.vshcherbakov.coiny.data.network.ApiHolder
import com.sandbox.vshcherbakov.coiny.domain.model.Provider
import com.sandbox.vshcherbakov.coiny.domain.repository.ProvidersRepository
import kotlinx.coroutines.Deferred

class ProvidersDataRepository(private val apiHolder: ApiHolder): ProvidersRepository {
    override fun getProviders(): Deferred<List<Provider>> {
        return apiHolder.provide().getProviders()
    }
}