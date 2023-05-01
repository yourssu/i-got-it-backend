package com.yourssu.igotIt.resolution.controller

import com.yourssu.igotIt.common.annotation.LoginUser
import com.yourssu.igotIt.resolution.dto.ResolutionCreateRequest
import com.yourssu.igotIt.resolution.dto.ResolutionCreateResponse
import com.yourssu.igotIt.resolution.dto.ResolutionGetResponse
import com.yourssu.igotIt.resolution.service.ResolutionService
import com.yourssu.igotIt.user.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class ResolutionController(
    private val resolutionService: ResolutionService
) {

    @PostMapping("/resolutions")
    fun create(
        @Validated @RequestBody request: ResolutionCreateRequest,
        @LoginUser user: User
    ) : ResponseEntity<ResolutionCreateResponse> {
        val response = resolutionService.create(request, user)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/resolutions/{resolutionId}")
    fun get(@PathVariable("resolutionId") resolutionUniqueId: String): ResponseEntity<ResolutionGetResponse> {
        val response = resolutionService.get(resolutionUniqueId)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/resolutions/{resolutionId}")
    fun delete(@PathVariable("resolutionId") resolutionUniqueId: String, @LoginUser user: User) {
        resolutionService.delete(resolutionUniqueId, user)
    }
}