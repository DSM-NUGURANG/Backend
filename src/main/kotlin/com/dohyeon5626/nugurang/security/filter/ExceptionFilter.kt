package com.dohyeon5626.nugurang.security.filter

import com.dohyeon5626.nugurang.error.ErrorCode
import com.dohyeon5626.nugurang.error.ErrorResponse
import com.dohyeon5626.nugurang.error.exception.GlobalException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter (
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            chain.doFilter(request, response)
        } catch (exception: Exception) {
            when (exception) {
                is GlobalException -> fromErrorResponse(exception.errorCode, exception.reason, response)
                else -> {
                    exception.printStackTrace()
                    fromErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "Sorry.... Server Error!!", response)
                }
            }
        }
    }

    private fun fromErrorResponse(errorCode: ErrorCode, reason: String, response: HttpServletResponse) {
        val errorResponse = ErrorResponse(errorCode, reason)
        response.status = errorResponse.status
        response.contentType = "application/json"
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}