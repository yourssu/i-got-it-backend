package com.yourssu.igotIt.utils.properties

import com.yourssu.igotIt.auth.infra.kakao.KakaoProperties
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
//@EnableConfigurationProperties(KakaoProperties::class)
//@TestPropertySource("classpath:application.yml")
class KakaoPropertiesTest(
    val kakaoProperties: KakaoProperties
) {

    @Test
    fun `kakao properties 조회`() {
        assertThat(kakaoProperties.clientId).isEqualTo("twoosky")
        assertThat(kakaoProperties.url.auth).isEqualTo("https://kauth.kakao.com")
        assertThat(kakaoProperties.url.api).isEqualTo("https://kapi.kakao.com")
    }
}