package com.example.testtaskavito.data.network

import com.example.testtaskavito.data.cart.GetCartsResponse
import com.example.testtaskavito.data.product.dto.ProductAllResponse
import com.example.testtaskavito.data.product.dto.list.CurrentProductResponse
import com.example.testtaskavito.data.user.dto.UserAllResponse
import com.example.testtaskavito.data.user.dto.list.UserDtoResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GlobalProjectApi {

    @GET("/app/v1/products")
    suspend fun getAllProduct(): ProductAllResponse

    @GET("/app/v1/products/{product_id}")
    suspend fun getProduct(@Path("product_id") productId: String): CurrentProductResponse

    @GET("/app/v1/users")
    suspend fun getAllUsers(): UserAllResponse

    @POST("/app/v1/users/auth/login")
    suspend fun getUsers(): UserDtoResponse

    @GET("/carts")
    suspend fun getAllCart(): GetCartsResponse
}