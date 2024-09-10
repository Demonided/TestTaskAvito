package com.example.testtaskavito.data.product.dto

import com.example.testtaskavito.data.product.dto.list.CurrentProductResponse
import com.example.testtaskavito.data.responce.Response
import com.google.gson.annotations.SerializedName

data class ProductAllResponse(
    val status: String,
    val count: Int,
    @SerializedName("Data")
    val dataProduct: List<CurrentProductResponse>
): Response()