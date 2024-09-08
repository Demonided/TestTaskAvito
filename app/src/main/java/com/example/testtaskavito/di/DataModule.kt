package com.example.testtaskavito.di

import com.example.testtaskavito.app.App
import com.example.testtaskavito.data.network.Endpoint
import com.example.testtaskavito.data.network.GlobalProjectApi
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        androidApplication() as App
    }

    single {
        Retrofit.Builder()
            .baseUrl(Endpoint.Avito.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GlobalProjectApi::class.java)
    }

    single<Gson> {
        Gson()
    }
}