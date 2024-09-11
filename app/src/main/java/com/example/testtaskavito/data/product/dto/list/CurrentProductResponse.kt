package com.example.testtaskavito.data.product.dto.list

import com.example.testtaskavito.data.responce.Response
import com.google.gson.annotations.SerializedName

data class CurrentProductResponse(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val category: List<String>,
    val price: Int,
    @SerializedName("discounted_price")
    val discountedPrice: Int,
    val edit: Boolean,
    val images: List<String>,
    val description: String,
    @SerializedName("product_rating")
    val productRating: Double,
    @SerializedName("product_specifications")
    val productSpecifications: List<Specification?>,
    val brand: String?,
): Response()

data class Specification(
    val key: String,
    val value: String
)