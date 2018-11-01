package com.sandbox.vshcherbakov.coiny.di

import com.sandbox.vshcherbakov.coiny.data.repository.PairsListDataRepository
import com.sandbox.vshcherbakov.coiny.domain.repository.PairsListRepository
import com.sandbox.vshcherbakov.coiny.domain.usecase.PairsListUseCase
import com.sandbox.vshcherbakov.coiny.ui.view_model.PairsListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val pairsListModule = module {
    viewModel { PairsListViewModel(get()) }
    single { PairsListUseCase(get()) }
    single { PairsListDataRepository(get()) as PairsListRepository }
}
