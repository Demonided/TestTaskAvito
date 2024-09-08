package com.example.testtaskavito.ui.search.vewmodel

import com.example.testtaskavito.domain.product.Product

sealed interface ProductState {
    data object Loading: ProductState

    data class Empty(
        val message: Int
    ) : ProductState

    data class Content(
        val content: List<Product>
    ) : ProductState

    data class Error(
        val message: String
    ) : ProductState
}