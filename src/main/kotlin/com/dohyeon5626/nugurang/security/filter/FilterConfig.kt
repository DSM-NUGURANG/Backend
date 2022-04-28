package com.dohyeon5626.nugurang.security.filter

import com.dohyeon5626.nugurang.security.jwt.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        val tokenFilter = JwtTokenFilter(jwtTokenProvider)
        builder.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
        val exceptionFilter = ExceptionFilter(objectMapper)
        builder.addFilterBefore(exceptionFilter, JwtTokenFilter::class.java)
    }
}