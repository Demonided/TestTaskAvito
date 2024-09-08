package com.example.testtaskavito.domain.productlist.impl

import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.domain.productlist.ProductListInteractor
import com.example.testtaskavito.domain.productlist.ProductListRepository
import com.example.testtaskavito.util.ResourceContentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductListInteractorImpl(
    private val productRepositoryList: ProductListRepository
) : ProductListInteractor {

    override fun searchListProduct(): Flow<Pair<List<Product>?, Int?>> {
        return productRepositoryList.searchListProduct().map { resource ->
            when(resource) {
                is ResourceContentSearch.SuccessSearch -> Pair(resource.data, null)
                is ResourceContentSearch.ErrorSearch -> Pair(null, resource.message)
                is ResourceContentSearch.ServerErrorSearch -> Pair(null, resource.message)
            }
        }
    }
}