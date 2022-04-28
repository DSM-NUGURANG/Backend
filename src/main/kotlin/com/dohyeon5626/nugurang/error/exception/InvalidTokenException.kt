package com.dohyeon5626.nugurang.error.exception

import com.dohyeon5626.nugurang.error.ErrorCode

class InvalidTokenException(reason: String): GlobalException(
    ErrorCode.INVALID_TOKEN, reason
)