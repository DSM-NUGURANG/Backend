package com.dohyeon5626.nugurang.config

import com.dohyeon5626.nugurang.config.properties.RedisProperties
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Configuration
class EmbeddedRedisConfig (
    private val redisProperties: RedisProperties
) {
    @PostConstruct
    fun runRedis() {
        if (redisServer == null) {
            redisServer = RedisServer(redisProperties.port)
            redisServer!!.start()
        }
    }

    @PreDestroy
    fun stopRedis() {
        redisServer?.let { redisServer: RedisServer-> redisServer.stop() }
    }

    companion object {
        private var redisServer: RedisServer? = null
    }
}