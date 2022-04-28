package com.dohyeon5626.nugurang.security.jwt

import com.dohyeon5626.nugurang.error.exception.InvalidTokenException
import com.dohyeon5626.nugurang.security.details.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val userLoadService: AuthDetailsService,
    private val jwtProperties: JwtProperties
) {
    fun generateAccessToken(value: String): String {
        return Jwts.builder()
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.access))
            .signWith(SignatureAlgorithm.HS512, jwtProperties.secret)
            .setIssuedAt(Date())
            .setSubject(value)
            .claim("type", "access")
            .compact()
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken: String? = request.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer "))
            bearerToken.substring(7)
        else null
    }

    fun getAuthentication(body: Claims): Authentication {
        if (!body["type", String::class.java].equals("access")) {
            throw InvalidTokenException("Token type is not access")
        }
        val details: UserDetails = userLoadService.loadUserByUsername(getId(body))
        return UsernamePasswordAuthenticationToken(details, "", details.authorities)
    }

    fun getBody(token: String): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secret).parseClaimsJws(token).body
        } catch (e: Exception) {
            throw InvalidTokenException("Parsing token is not possible")
        }
    }

    fun getId(body: Claims): String {
        return body.subject
    }
}