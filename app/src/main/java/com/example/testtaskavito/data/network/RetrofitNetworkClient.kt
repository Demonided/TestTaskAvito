package com.example.testtaskavito.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.testtaskavito.data.product.request.ProductAllRequest
import com.example.testtaskavito.data.product.request.ProductDtoRequest
import com.example.testtaskavito.data.responce.Response
import com.example.testtaskavito.data.responce.ResponseCodes
import com.example.testtaskavito.data.user.dto.request.UserAllRequest
import com.example.testtaskavito.data.user.dto.request.UserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class RetrofitNetworkClient(
private val context: Context,
    private val productApi: GlobalProjectApi
): NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return createNoConnectionResponse()
        }

        return executeRequest(dto)
    }

    private suspend fun executeRequest(dto: Any): Response {
        if (!isValidDto(dto)) {
            return createErrorResponse()
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = when (dto) {
                    is UserAllRequest -> {
                        productApi.getAllUsers()
                    }

                    is UserRequest -> {
                        productApi.getUsers()
                    }

                    is ProductAllRequest -> {
                        productApi.getAllProduct()
                    }

                    is ProductDtoRequest -> {
                        productApi.getProduct(dto.productId)
                    }

                    else -> throw IllegalArgumentException("Invalid DTO type: $dto")
                }
                response.apply { resultCode = ResponseCodes.SUCCESS }

            } catch (e: IOException) {
                Log.e("IOException", e.message.toString())
                Response().apply { resultCode = ResponseCodes.SERVER_ERROR }
            } catch (e: HttpException) {
                Log.e("HttpException", e.toString())
                Response().apply { resultCode = ResponseCodes.SERVER_ERROR }
            }

        }
    }

    private fun isValidDto(dto: Any): Boolean {
        return isValidUserDto(dto) || isValidProductDto(dto)
    }

    private fun isValidUserDto(dto: Any): Boolean {
        return dto is UserAllRequest || dto is UserRequest
    }

    private fun isValidProductDto(dto: Any): Boolean {
        return dto is ProductAllRequest || dto is ProductDtoRequest
    }

    private fun createNoConnectionResponse(): Response {
        return Response().apply { resultCode = ResponseCodes.NO_CONNECTION }
    }

    private fun createErrorResponse(): Response {
        return Response().apply { resultCode = ResponseCodes.ERROR }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        return capabilities?.run {
            return hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }
}