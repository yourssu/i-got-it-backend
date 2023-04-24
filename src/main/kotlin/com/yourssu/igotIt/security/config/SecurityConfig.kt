package com.yourssu.igotIt.security.config

import com.yourssu.igotIt.security.jwt.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtProvider: JwtProvider
) {

    // TODO: 로그인 안한 사용자 - 쪽지 생성, 결심 조회도 풀어야됨
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring()
                .regexMatchers("/api/v1/auth/sign-in/kakao")
                .regexMatchers("/api/v1/auth/sign-in/info")
                .regexMatchers("/")  // swagger
        }
    }

    @Bean
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .headers { header -> header.frameOptions { it.disable() }}
            .csrf { it.disable() }
            .cors { it.disable() }
            .logout { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { it.anyRequest().authenticated()
                .and()
                .apply(JwtSecurityConfig(jwtProvider))
            }

        return http.build()
    }
}