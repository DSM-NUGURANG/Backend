package com.dohyeon5626.nugurang.error.exception

import com.dohyeon5626.nugurang.error.ErrorCode

class GlobalException(
    val errorCode: ErrorCode,
    val reason: String
) : RuntimeException(
    errorCode.message
)