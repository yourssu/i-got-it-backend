package com.yourssu.igotIt.letter.controller

import com.yourssu.igotIt.letter.dto.LetterCreateRequest
import com.yourssu.igotIt.letter.dto.LetterCreateResponse
import com.yourssu.igotIt.letter.dto.LetterGetResponse
import com.yourssu.igotIt.letter.service.LetterService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Id

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

    @GetMapping("/resolutions/{resolutionId}/letters")
    fun get(
        @RequestParam userId: Long? = null,
        @PathVariable resolutionId: Long): ResponseEntity<LetterGetResponse> {
        val response = letterService.get(resolutionId, userId)
        return ResponseEntity.ok(response)
    }
}