package com.example.testtaskavito.di

import com.example.testtaskavito.app.App
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single {
        androidApplication() as App
    }
}