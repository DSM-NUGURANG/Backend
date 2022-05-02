package com.dohyeon5626.nugurang.entity.redis

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@RedisHash(timeToLive = 300)
class ChangePasswordCertify constructor(
    @field:Indexed private val email: String,
    private val password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
}