package com.example.testtaskavito.domain.productlist

import com.example.testtaskavito.domain.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductListInteractor {
    fun searchListProduct(): Flow<Pair<List<Product>?, Int?>>
}