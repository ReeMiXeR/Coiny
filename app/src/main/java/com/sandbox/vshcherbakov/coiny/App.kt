package com.sandbox.vshcherbakov.coiny

import android.app.Application
import com.sandbox.vshcherbakov.coiny.di.*
import org.koin.android.ext.android.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Koin
        startKoin(this, listOf(
                commonModule,
                articleListModule,
                articleModule,
                providersModule,
                pairsListModule))
    }
}