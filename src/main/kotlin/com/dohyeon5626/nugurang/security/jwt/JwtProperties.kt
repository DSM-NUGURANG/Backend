package com.dohyeon5626.nugurang.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.*

@ConstructorBinding
@ConfigurationProperties(prefix = "auth.jwt")
class JwtProperties(secret: String, access: Long) {
    val secret: String
    val access: Long

    init {
        this.secret = Base64.getEncoder().encodeToString(secret.toByteArray())
        this.access = access
    }
}