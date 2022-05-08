package com.dohyeon5626.nugurang.error.exception

import com.dohyeon5626.nugurang.error.ErrorCode

class NotFoundException(reason: String): GlobalException(
    ErrorCode.NOT_FOUND, reason
)