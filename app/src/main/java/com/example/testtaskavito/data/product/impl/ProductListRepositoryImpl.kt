package com.example.testtaskavito.data.product.impl

import com.example.testtaskavito.data.converter.ConverterProduct.mapToProductList
import com.example.testtaskavito.data.network.NetworkClient
import com.example.testtaskavito.data.product.dto.TotalNumberProductsResponse
import com.example.testtaskavito.data.product.request.ProductAllRequest
import com.example.testtaskavito.data.responce.ResponseCodes
import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.domain.productlist.ProductListRepository
import com.example.testtaskavito.util.ResourceContentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductListRepositoryImpl(
    private val networkClient: NetworkClient
) : ProductListRepository {

    override fun searchListProduct(): Flow<ResourceContentSearch<List<Product>>> = flow {
        val response = networkClient.doRequest(ProductAllRequest)

        when(response.resultCode) {
            ResponseCodes.SUCCESS -> {
                try {
                    val product = (response as TotalNumberProductsResponse).dataProduct
                    emit(ResourceContentSearch.SuccessSearch(product.mapToProductList()))
                } catch (e: Exception) {
                    emit(ResourceContentSearch.ErrorSearch(response.resultCode.code))
                    throw e
                }
            }
            ResponseCodes.DEFAULT -> emit(ResourceContentSearch.ErrorSearch(response.resultCode.code))
            ResponseCodes.NO_CONNECTION -> emit(ResourceContentSearch.ErrorSearch(response.resultCode.code))
            ResponseCodes.ERROR  -> emit(ResourceContentSearch.ErrorSearch(response.resultCode.code))
            ResponseCodes.SERVER_ERROR -> emit(ResourceContentSearch.ErrorSearch(response.resultCode.code))
        }
    }
}