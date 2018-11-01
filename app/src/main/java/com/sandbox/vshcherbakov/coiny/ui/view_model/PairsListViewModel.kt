package com.sandbox.vshcherbakov.coiny.ui.view_model

import android.arch.lifecycle.ViewModel
import com.sandbox.vshcherbakov.coiny.domain.utils.SingleLiveEvent
import com.sandbox.vshcherbakov.coiny.domain.model.CurrencyPair
import com.sandbox.vshcherbakov.coiny.domain.usecase.PairsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class PairsListViewModel(private val pairsListUseCase: PairsListUseCase) : ViewModel() {
    val state = SingleLiveEvent<PairsListState>()
    val events = SingleLiveEvent<PairsListEvent>()

    init {
        state.postValue(PairsListState.Loading)
    }


    fun loadPairsList() = runBlocking {
        repeat(300) {
            async(Dispatchers.Default) {
                state.postValue(PairsListState.Content(pairsListUseCase.getPairsList()))
                delay(1000)
            }
        }
    }
}

sealed class PairsListEvent

sealed class PairsListState {
    object Loading : PairsListState()
    object Empty : PairsListState()
    object Stub : PairsListState()
    data class Content(val currencyPairs: List<CurrencyPair>) : PairsListState()
}
