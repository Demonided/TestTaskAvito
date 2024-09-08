package com.example.testtaskavito.data.responce

enum class ResponseCodes(val code: Int) {
    DEFAULT(ResponseCodeConstants.DEFAULT),
    NO_CONNECTION(ResponseCodeConstants.NO_CONNECTION),
    SUCCESS(ResponseCodeConstants.SUCCESS),
    ERROR(ResponseCodeConstants.ERROR),
    SERVER_ERROR(ResponseCodeConstants.SERVER_ERROR)
}