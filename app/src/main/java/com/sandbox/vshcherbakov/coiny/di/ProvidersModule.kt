package com.sandbox.vshcherbakov.coiny.di

import com.sandbox.vshcherbakov.coiny.data.repository.ProvidersDataRepository
import com.sandbox.vshcherbakov.coiny.domain.repository.ProvidersRepository
import com.sandbox.vshcherbakov.coiny.domain.usecase.ProvidersUseCase
import com.sandbox.vshcherbakov.coiny.ui.view_model.ProvidersViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val providersModule = module {
    viewModel { ProvidersViewModel(get()) }
    single { ProvidersUseCase(get()) }
    single { ProvidersDataRepository(get()) as ProvidersRepository }
}