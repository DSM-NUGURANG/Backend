package com.dohyeon5626.nugurang.security.filter

import com.dohyeon5626.nugurang.security.jwt.JwtTokenProvider
import io.jsonwebtoken.Claims
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter (
    private val jwtTokenProvider: JwtTokenProvider
): OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        jwtTokenProvider.resolveToken(request)
            ?.let { it: String -> jwtTokenProvider.getBody(it) }
            ?.let { it: Claims -> jwtTokenProvider.getAuthentication(it) }
            .apply { SecurityContextHolder.getContext().authentication = this }
        chain.doFilter(request, response)
    }
}