package com.example.testtaskavito.domain.product

import kotlinx.coroutines.flow.StateFlow

interface ProductRepositoryFlow {

    fun setProductFlow(product: Product?)

    fun getProductFlow(): StateFlow<Product?>
}