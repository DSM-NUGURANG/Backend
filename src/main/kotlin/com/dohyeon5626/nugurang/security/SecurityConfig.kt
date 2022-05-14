package com.dohyeon5626.nugurang.security

import com.dohyeon5626.nugurang.security.filter.FilterConfig
import com.dohyeon5626.nugurang.security.jwt.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .sessionManagement().disable()
            .formLogin().disable().cors()
            .and().authorizeRequests()
            .antMatchers("/user", "/user/**").authenticated()
            .anyRequest().permitAll()
            .and().apply(FilterConfig(jwtTokenProvider, objectMapper))
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/favicon.ico")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}