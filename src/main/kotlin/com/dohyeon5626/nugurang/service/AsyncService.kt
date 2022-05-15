package com.dohyeon5626.nugurang.service

import com.dohyeon5626.nugurang.dto.response.PositionResponse
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class AsyncService {
    @Async
    fun send(emitter: SseEmitter, response: PositionResponse) {
        Thread.sleep(5000L)
        try {
            emitter.send(response)
        } catch (e: Exception) {
            Thread.sleep(5000L)
            emitter.send(response)
        }
    }
}