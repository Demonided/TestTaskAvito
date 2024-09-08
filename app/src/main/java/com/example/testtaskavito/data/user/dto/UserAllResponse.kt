package com.example.testtaskavito.data.user.dto

import com.example.testtaskavito.data.responce.Response
import com.example.testtaskavito.data.user.dto.list.UserDtoResponse

data class UserAllResponse(
    val status: String,
    val count: Int,
    val dataProduct: List<UserDtoResponse>
): Response()