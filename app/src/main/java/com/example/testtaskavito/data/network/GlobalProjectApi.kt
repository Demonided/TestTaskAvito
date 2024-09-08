package com.example.testtaskavito.data.network

import com.example.testtaskavito.data.cart.GetCartsResponse
import com.example.testtaskavito.data.product.dto.TotalNumberProductsResponse
import com.example.testtaskavito.data.product.dto.list.CurrentProductResponse
import com.example.testtaskavito.data.user.dto.UserAllResponse
import com.example.testtaskavito.data.user.dto.list.UserDtoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GlobalProjectApi {

    @GET("/product")
    suspend fun getAllProduct(): TotalNumberProductsResponse

    @GET("/product/{product_id}")
    suspend fun getProduct(@Path("product_id") productId: String): CurrentProductResponse

    @GET("/users")
    suspend fun getAllUsers(): UserAllResponse

    @GET("/users/auth/login")
    suspend fun getUsers(): UserDtoResponse

    @GET("/carts")
    suspend fun getAllCart(): GetCartsResponse
}