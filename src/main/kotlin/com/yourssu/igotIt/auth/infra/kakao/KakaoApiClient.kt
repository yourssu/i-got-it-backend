package com.yourssu.igotIt.auth.infra.kakao

import com.yourssu.igotIt.auth.domain.OAuthApiClient
import com.yourssu.igotIt.auth.domain.OAuthInfo
import com.yourssu.igotIt.auth.domain.OAuthLoginRequest
import com.yourssu.igotIt.auth.domain.OAuthProvider
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

private const val GRANT_TYPE = "authorization_code"

@Service
class KakaoApiClient(
    private val restTemplate: RestTemplate,
    private val kakaoProperties: KakaoProperties
) : OAuthApiClient {

    override fun oAuthProvider(): OAuthProvider {
        return OAuthProvider.KAKAO
    }

    override fun requestAccessToken(dto: OAuthLoginRequest): String {
        val url = "${kakaoProperties.url.auth}/oauth/token"

        val httpHeaders = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
        }

        val body = dto.makeBody().apply {
            add("grant_type", GRANT_TYPE)
            add("client_id", kakaoProperties.clientId)
        }

        val request = HttpEntity<MultiValueMap<String, String>>(body, httpHeaders)
        val response: KakaoTokens = restTemplate.postForObject(url, request, KakaoTokens::class)

        return response.accessToken
    }

    override fun requestOauthInfo(accessToken: String): OAuthInfo {
        val url = "${kakaoProperties.url.api}/v2/user/me"

        val httpHeaders = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            set("Authorization", "Bearer $accessToken")
        }

        val body = LinkedMultiValueMap<String, String>().apply {
            add("property_keys", "[\"kakao_account.email\"]")
        }

        val request = HttpEntity<MultiValueMap<String, String>>(body, httpHeaders)

        val response: KakaoInfo = restTemplate.postForObject(url, request, KakaoInfo::class)
        return response

//        return restTemplate.postForObject(url, request, KakaoInfo::class)
    }
}