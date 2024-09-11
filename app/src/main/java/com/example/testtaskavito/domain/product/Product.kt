package com.example.testtaskavito.domain.product

import com.example.testtaskavito.data.product.dto.list.Specification
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val category: List<String>,
    val price: Int,
    @SerializedName("discounted_price")
    val discountedPrice: Int?,
    val edit: Boolean,
    val images: List<String>,
    val description: String,
    @SerializedName("product_rating")
    val productRating: Double,
    @SerializedName("product_specifications")
    val productSpecifications: List<Specification?>,
    val brand: String?
)