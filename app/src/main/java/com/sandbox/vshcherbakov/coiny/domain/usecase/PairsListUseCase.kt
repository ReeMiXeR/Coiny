package com.sandbox.vshcherbakov.coiny.domain.usecase

import android.os.Looper
import android.util.Log
import com.sandbox.vshcherbakov.coiny.domain.model.CurrencyPair
import com.sandbox.vshcherbakov.coiny.domain.repository.PairsListRepository

class PairsListUseCase(private val pairsListRepository: PairsListRepository) : BaseUseCase() {

    suspend fun getPairsList(): List<CurrencyPair> {
        Log.e("useCase", "is main thread - ${Looper.getMainLooper() == Looper.myLooper()}")
        return pairsListRepository.getProviders()
    }
}
