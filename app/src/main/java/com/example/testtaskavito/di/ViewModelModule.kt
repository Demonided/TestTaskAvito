package com.example.testtaskavito.di

import com.example.testtaskavito.ui.product.viewmodel.ProductViewModel
import com.example.testtaskavito.ui.search.vewmodel.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel {
        ProductListViewModel(get(), get())
    }

    viewModel {
        ProductViewModel(get())
    }
}