package com.yourssu.igotIt.auth.controller

import com.yourssu.igotIt.auth.dto.AccessTokenRefreshResponse
import com.yourssu.igotIt.auth.dto.LoginInfoRequestDto
import com.yourssu.igotIt.auth.dto.LoginResponseDto
import com.yourssu.igotIt.auth.infra.kakao.KakaoLoginRequest
import com.yourssu.igotIt.auth.service.AuthService
import com.yourssu.igotIt.common.annotation.LoginUser
import com.yourssu.igotIt.user.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/sign-in/kakao")
    fun signInKakao(
        @RequestBody request: KakaoLoginRequest
    ): ResponseEntity<LoginResponseDto> {
        return ResponseEntity.ok(authService.login(request))
    }

    @PostMapping("/sign-in/info")
    fun signInInfo(
        @LoginUser user: User,
        @Validated @RequestBody request: LoginInfoRequestDto
    ) {
        authService.updateInfo(request, user)
    }

    @PostMapping("/token/refresh")
    fun refresh(@LoginUser user: User): ResponseEntity<AccessTokenRefreshResponse> {
        val response = authService.refresh(user)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/withdraw")
    fun withdraw(@LoginUser user: User) {
        authService.withdraw(user)
    }
}