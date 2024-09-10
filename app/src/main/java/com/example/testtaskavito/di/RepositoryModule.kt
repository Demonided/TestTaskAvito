package com.example.testtaskavito.di

import com.example.testtaskavito.data.product.ProductRepositoryFlowImpl
import com.example.testtaskavito.data.product.impl.ProductListRepositoryImpl
import com.example.testtaskavito.domain.product.ProductRepositoryFlow
import com.example.testtaskavito.domain.productlist.ProductListRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<ProductListRepository> {
        ProductListRepositoryImpl(get())
    }

    single<ProductRepositoryFlow> {
        ProductRepositoryFlowImpl()
    }
}