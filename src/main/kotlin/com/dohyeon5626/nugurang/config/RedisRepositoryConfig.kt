package com.dohyeon5626.nugurang.config

import com.dohyeon5626.nugurang.config.properties.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableRedisRepositories
class RedisRepositoryConfig (
    private val redisProperties: RedisProperties
) {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<Array<Byte>, Array<Byte>> {
        val redisTemplate = RedisTemplate<Array<Byte>, Array<Byte>>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}