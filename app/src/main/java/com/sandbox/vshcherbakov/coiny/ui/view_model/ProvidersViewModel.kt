package com.sandbox.vshcherbakov.coiny.ui.view_model

import android.arch.lifecycle.ViewModel
import com.sandbox.vshcherbakov.coiny.domain.utils.SingleLiveEvent
import com.sandbox.vshcherbakov.coiny.domain.model.Provider
import com.sandbox.vshcherbakov.coiny.domain.usecase.ProvidersUseCase
import com.sandbox.vshcherbakov.coiny.ui.view.TransitionElements
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProvidersViewModel(private val providersUseCase: ProvidersUseCase) : ViewModel() {
    val state = SingleLiveEvent<ProvidersState>()
    val events = SingleLiveEvent<ProvidersEvent>()

    init {
        state.postValue(ProvidersState.Loading)
    }

    fun loadProvidersList() {
        GlobalScope.launch(Dispatchers.Main) {
            val providers = providersUseCase.getProviders()
            val newState = ProvidersState.Content(providers.await())
            state.postValue(newState)
        }
    }

    fun onProviderItemClick(transitionElements: TransitionElements, provider: Provider) {
//        val event = ProvidersEvent.SwitchToArticle(transitionElements, provider)
//        events.postValue(event)
    }
}

sealed class ProvidersEvent {
    data class SwitchToArticle(
            val transitionElements: TransitionElements,
            val provider: Provider
    ) : ProvidersEvent()
}

sealed class ProvidersState {
    object Loading : ProvidersState()
    object Empty : ProvidersState()
    object Stub : ProvidersState()
    data class Content(val providers: List<Provider>) : ProvidersState()
}
