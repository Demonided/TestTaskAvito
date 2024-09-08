package com.example.testtaskavito.di

import com.example.testtaskavito.domain.productlist.ProductListInteractor
import com.example.testtaskavito.domain.productlist.impl.ProductListInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<ProductListInteractor> {
        ProductListInteractorImpl(get())
    }
}