package com.example.testtaskavito.data.product.dto

import com.example.testtaskavito.data.product.dto.list.CurrentProductResponse
import com.example.testtaskavito.data.responce.Response

data class TotalNumberProductsResponse(
    val status: String,
    val count: Int,
    val dataProduct: List<CurrentProductResponse>
): Response()