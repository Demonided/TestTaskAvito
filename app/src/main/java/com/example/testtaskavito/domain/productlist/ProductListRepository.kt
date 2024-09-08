package com.example.testtaskavito.domain.productlist

import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.util.ResourceContentSearch
import kotlinx.coroutines.flow.Flow

interface ProductListRepository {
    fun searchListProduct(): Flow<ResourceContentSearch<List<Product>>>
}