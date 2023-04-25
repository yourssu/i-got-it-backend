package com.yourssu.igotIt.resolution.controller

import com.yourssu.igotIt.common.annotation.LoginUser
import com.yourssu.igotIt.resolution.dto.ResolutionCreateRequest
import com.yourssu.igotIt.resolution.dto.ResolutionCreateResponse
import com.yourssu.igotIt.resolution.service.ResolutionService
import com.yourssu.igotIt.user.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ResolutionController(
    private val resolutionService: ResolutionService
) {

    @PostMapping("/resolutions")
    fun create(
        @RequestBody request: ResolutionCreateRequest,
        @LoginUser user: User
    ) : ResponseEntity<ResolutionCreateResponse> {
        val response = resolutionService.create(request, user)
        return ResponseEntity.ok(response)
    }
}