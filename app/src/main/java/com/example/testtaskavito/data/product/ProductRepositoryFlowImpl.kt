package com.example.testtaskavito.data.product

import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.domain.product.ProductRepositoryFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductRepositoryFlowImpl : ProductRepositoryFlow {

    private val productFlow: MutableStateFlow<Product?> = MutableStateFlow(null)

    override fun setProductFlow(product: Product?) {
        productFlow.value = product
    }

    override fun getProductFlow(): StateFlow<Product?> = productFlow
}