package com.example.testtaskavito.data.network

import com.example.testtaskavito.data.responce.Response

interface NetworkClient {

    suspend fun doRequest(dto: Any): Response
}