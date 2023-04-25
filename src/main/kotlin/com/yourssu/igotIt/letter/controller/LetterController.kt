package com.yourssu.igotIt.letter.controller

import com.yourssu.igotIt.letter.dto.LetterCreateRequest
import com.yourssu.igotIt.letter.dto.LetterCreateResponse
import com.yourssu.igotIt.letter.service.LetterService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class LetterController(
    private val letterService: LetterService
) {

    @PostMapping("/resolutions/{resolutionId}/letters")
    fun create(
        @Validated @RequestBody request: LetterCreateRequest,
        @PathVariable resolutionId: Long
    ) : ResponseEntity<LetterCreateResponse> {
        val response = letterService.create(request, resolutionId)
        return ResponseEntity.ok(response)
    }
}