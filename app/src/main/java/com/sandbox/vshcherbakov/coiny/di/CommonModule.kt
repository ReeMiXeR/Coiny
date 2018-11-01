package com.sandbox.vshcherbakov.coiny.di

import com.sandbox.vshcherbakov.coiny.data.network.ApiHolder
import org.koin.dsl.module.module

val commonModule = module {
    single { ApiHolder() }
}