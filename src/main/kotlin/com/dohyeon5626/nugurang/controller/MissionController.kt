package com.dohyeon5626.nugurang.controller

import com.dohyeon5626.nugurang.dto.request.PositionRequest
import com.dohyeon5626.nugurang.security.AuthenticationFacade
import com.dohyeon5626.nugurang.service.MissionService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/mission")
class MissionController (
    private val missionService: MissionService,
    private val authenticationFacade: AuthenticationFacade
) {
    @PostMapping("/easy", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun easyMission(@RequestBody request: PositionRequest): SseEmitter {
        return missionService.makeEasyMission(authenticationFacade.getUserId(), request)
    }

    @PostMapping("/normal", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun normalMission(@RequestBody request: PositionRequest): SseEmitter {
        return missionService.makeNormalMission(authenticationFacade.getUserId(), request)
    }

    @PostMapping("/hard", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun hardMission(@RequestBody request: PositionRequest): SseEmitter {
        return missionService.makeHardMission(authenticationFacade.getUserId(), request)
    }

    @PostMapping
    fun finishMission() {
        missionService.finishMission(authenticationFacade.getUserId())
    }

    @DeleteMapping
    fun giveUpMission() {
        missionService.giveUpMission(authenticationFacade.getUserId())
    }
}