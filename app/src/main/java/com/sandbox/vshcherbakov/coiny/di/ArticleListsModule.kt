package com.sandbox.vshcherbakov.coiny.di

import com.sandbox.vshcherbakov.coiny.data.repository.ArticlesDataRepository
import com.sandbox.vshcherbakov.coiny.data.network.ApiHolder
import com.sandbox.vshcherbakov.coiny.data.repository.ProvidersDataRepository
import com.sandbox.vshcherbakov.coiny.domain.repository.ArticlesRepository
import com.sandbox.vshcherbakov.coiny.domain.repository.ProvidersRepository
import com.sandbox.vshcherbakov.coiny.domain.usecase.ArticleListUseCase
import com.sandbox.vshcherbakov.coiny.domain.usecase.ProvidersUseCase
import com.sandbox.vshcherbakov.coiny.ui.view_model.ArticleListViewModel
import com.sandbox.vshcherbakov.coiny.ui.view_model.ArticleViewModel
import com.sandbox.vshcherbakov.coiny.ui.view_model.ProvidersViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val articleListModule = module {
    viewModel { ArticleListViewModel(get()) }
    single { ArticleListUseCase(get()) }
    single { ArticlesDataRepository(get()) as ArticlesRepository  }
}
