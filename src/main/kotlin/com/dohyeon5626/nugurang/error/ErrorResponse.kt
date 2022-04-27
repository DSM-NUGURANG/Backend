package com.dohyeon5626.nugurang.error

class ErrorResponse(
    errorCode: ErrorCode,
    val reason: String
) {

    val status: Int = errorCode.status
    val message: String = errorCode.message

}