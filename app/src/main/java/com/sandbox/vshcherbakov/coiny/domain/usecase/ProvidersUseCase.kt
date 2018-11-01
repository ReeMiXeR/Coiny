package com.sandbox.vshcherbakov.coiny.domain.usecase

import com.sandbox.vshcherbakov.coiny.domain.model.Provider
import com.sandbox.vshcherbakov.coiny.domain.repository.ProvidersRepository
import kotlinx.coroutines.Deferred

class ProvidersUseCase(private val providersRepository: ProvidersRepository) : BaseUseCase() {

    fun getProviders(): Deferred<List<Provider>> {
        return providersRepository.getProviders()
    }
}
