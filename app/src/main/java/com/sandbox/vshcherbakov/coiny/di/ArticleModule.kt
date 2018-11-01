package com.sandbox.vshcherbakov.coiny.di

import com.sandbox.vshcherbakov.coiny.ui.view_model.ArticleViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val articleModule = module {
    viewModel { ArticleViewModel() }
}
