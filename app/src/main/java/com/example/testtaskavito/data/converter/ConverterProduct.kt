package com.example.testtaskavito.data.converter

import com.example.testtaskavito.data.product.dto.list.CurrentProductResponse
import com.example.testtaskavito.domain.product.Product

object ConverterProduct {

    fun CurrentProductResponse.mapToProduct(): Product = Product(
        id = id,
        name = name,
        category = category,
        price = price,
        discountedPrice = discountedPrice,
        edit = edit,
        images = images,
        description = description,
        productRating = productRating,
        productSpecifications = productSpecifications,
        brand = brand
    )

    fun List<CurrentProductResponse>.mapToProductList(): List<Product> = map { it.mapToProduct() }
}