package com.dohyeon5626.nugurang.log

import mu.KLogging
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LogInterceptor: HandlerInterceptor {
    companion object: KLogging()

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        exception: Exception?
    ) {
        if (response.status in 400..499)
            logger.info("[${response.status}] - ${request.method} | ${request.requestURI} | ${request.getHeader("User-Agent")}")
        else if (response.status >= 500)
            logger.error("[${response.status}] - ${request.method} | ${request.requestURI} | ${request.getHeader("User-Agent")}")
    }
}