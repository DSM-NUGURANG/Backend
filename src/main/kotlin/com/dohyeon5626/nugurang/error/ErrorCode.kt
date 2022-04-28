package com.dohyeon5626.nugurang.error

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400, "Bad Request"),
    INVALID_TOKEN(401, "Invalid Token")
}