package com.yourssu.igotIt.letter.controller

import com.yourssu.igotIt.common.annotation.LoginUser
import com.yourssu.igotIt.letter.dto.LetterCreateRequest
import com.yourssu.igotIt.letter.dto.LetterCreateResponse
import com.yourssu.igotIt.letter.dto.LetterGetResponse
import com.yourssu.igotIt.letter.service.LetterService
import com.yourssu.igotIt.user.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class LetterController(
    private val letterService: LetterService
) {

    @PostMapping("/resolutions/{resolutionId}/letters")
    fun create(
        @Validated @RequestBody request: LetterCreateRequest,
        @PathVariable("resolutionId") resolutionUniqueId: String
    ) : ResponseEntity<LetterCreateResponse> {
        val response = letterService.create(request, resolutionUniqueId)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/resolutions/{resolutionId}/letters")
    fun get(
        @RequestParam userId: Long? = null,
        @PathVariable("resolutionId") resolutionUniqueId: String
    ): ResponseEntity<LetterGetResponse> {
        val response = letterService.get(resolutionUniqueId, userId)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/resolutions/{resolutionId}/letters/{letterId}")
    fun delete(
        @PathVariable("resolutionId") resolutionUniqueId: String,
        @PathVariable letterId: Long,
        @LoginUser user: User
    ) {
        letterService.delete(resolutionUniqueId, letterId, user)
    }
}