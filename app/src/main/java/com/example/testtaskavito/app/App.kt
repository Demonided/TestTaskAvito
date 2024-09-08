package com.example.testtaskavito.app

import android.app.Application
import com.example.testtaskavito.di.dataModule
import com.example.testtaskavito.di.interactorModule
import com.example.testtaskavito.di.repositoryModule
import com.example.testtaskavito.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(dataModule, repositoryModule, interactorModule, viewModel)
        }
    }
}