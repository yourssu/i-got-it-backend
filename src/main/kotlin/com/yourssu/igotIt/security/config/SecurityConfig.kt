package com.yourssu.igotIt.security.config

import com.yourssu.igotIt.security.jwt.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val jwtProvider: JwtProvider,
    private val authenticationEntryPoint: AuthenticationEntryPoint
) {

    // TODO: 로그인 안한 사용자 - 쪽지 생성, 결심 조회도 풀어야됨
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring()
                .antMatchers("/api/v1/auth/sign-in/kakao")
                .antMatchers(HttpMethod.POST, "/api/v1/resolutions/*/letters")
                .antMatchers(HttpMethod.GET, "/api/v1/resolutions/*")
                .antMatchers(HttpMethod.GET, "/api/v1/resolutions/*/letters")
                .antMatchers("/")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/v3/api-docs/**")
                .antMatchers("/api/v1/mail/test")
                .antMatchers("/api/v1/resolutions/history/count")
        }
    }

    @Bean(value = ["securityConfigFilterChain"])
    @Order(1)
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .headers { header -> header.frameOptions { it.disable() }}
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .logout { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { it.anyRequest().authenticated()
                .and()
                .apply(JwtSecurityConfig(jwtProvider, authenticationEntryPoint))
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        CorsConfiguration().apply {
            addAllowedOriginPattern("*")
            addAllowedHeader("*")
            addAllowedMethod("*")
            allowCredentials = true

            val source = UrlBasedCorsConfigurationSource()
            source.registerCorsConfiguration("/**", this)
            return source
        }
    }
}