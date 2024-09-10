package com.example.testtaskavito.di

import com.example.testtaskavito.app.App
import com.example.testtaskavito.data.network.Endpoint
import com.example.testtaskavito.data.network.GlobalProjectApi
import com.example.testtaskavito.data.network.NetworkClient
import com.example.testtaskavito.data.network.RetrofitNetworkClient
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        androidApplication() as App
    }

    single<NetworkClient> {
        RetrofitNetworkClient(androidContext(), get())
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Уровень логирования: BODY, HEADER, BASIC, NONE
        }

        // Создаем OkHttpClient с настройками, например, добавляем интерсептор логирования
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Добавляем интерсептор для логирования
            .build()

        Retrofit.Builder()
            .baseUrl(Endpoint.Avito.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GlobalProjectApi::class.java)
    }

    single<Gson> {
        Gson()
    }
}