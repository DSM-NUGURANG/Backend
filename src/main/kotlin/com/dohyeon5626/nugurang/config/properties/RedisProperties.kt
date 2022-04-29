package com.dohyeon5626.nugurang.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "spring.redis")
class RedisProperties (
    val host: String,
    val port: Int
)