package com.yourssu.igotIt.auth.service

import com.yourssu.igotIt.auth.domain.OAuthApiClient
import com.yourssu.igotIt.auth.domain.OAuthInfo
import com.yourssu.igotIt.auth.domain.OAuthLoginRequest
import com.yourssu.igotIt.auth.domain.OAuthProvider
import org.springframework.stereotype.Service

@Service
class RequestOAuthInfoService(
    clients: List<OAuthApiClient>
) {
    private val clients: Map<OAuthProvider, OAuthApiClient>

    init {
        this.clients = clients.associateBy { oAuthApiClient ->  oAuthApiClient.oAuthProvider() }
    }

    fun request(dto: OAuthLoginRequest): OAuthInfo {
        val client = clients[dto.oAuthProvider()]
            ?: throw RuntimeException("존재하지 않는 OAuthProvider 입니다.")

        val accessToken = client.requestAccessToken(dto)

        return client.requestOauthInfo(accessToken)
    }
}