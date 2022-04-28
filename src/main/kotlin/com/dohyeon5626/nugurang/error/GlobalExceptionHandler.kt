package com.dohyeon5626.nugurang.error

import com.dohyeon5626.nugurang.error.exception.GlobalException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class, HttpMessageNotReadableException::class)
    protected fun handleMethodArgumentNotValidException(e: Exception): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(ErrorCode.BAD_REQUEST, e.message!!)
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(response.status))
    }

    @ExceptionHandler(GlobalException::class)
    protected fun handleGlobalException(e: GlobalException): ResponseEntity<ErrorResponse> {
        val errorCode: ErrorCode = e.errorCode
        val response = ErrorResponse(errorCode, e.reason)
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        e.printStackTrace()
        val response = ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "Sorry.... Server Error!!")
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(response.status))
    }
}