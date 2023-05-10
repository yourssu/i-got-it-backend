package com.yourssu.igotIt.resolution.controller

import com.yourssu.igotIt.resolution.dto.ResolutionHistoryCountResponse
import com.yourssu.igotIt.resolution.service.ResolutionHistoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ResolutionHistoryController(
    private val resolutionHistoryService: ResolutionHistoryService
) {

    @GetMapping("/resolutions/history/count")
    fun count(): ResponseEntity<ResolutionHistoryCountResponse> {
        val response = resolutionHistoryService.count()
        return ResponseEntity.ok(response)
    }
}