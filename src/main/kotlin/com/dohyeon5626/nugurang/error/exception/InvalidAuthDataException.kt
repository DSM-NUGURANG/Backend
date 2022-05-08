package com.dohyeon5626.nugurang.error.exception

import com.dohyeon5626.nugurang.error.ErrorCode

class InvalidAuthDataException(reason: String): GlobalException(
    ErrorCode.INVALID_AUTH_DATA, reason
)