package com.yourssu.igotIt.common.config

import com.yourssu.igotIt.common.annotation.LoginUser
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

const val SECURITY_SCHEME_KEY = "JWT"
const val SECURITY_SCHEME = "Bearer"
const val SECURITY_BEARER_FORMAT = "JWT"

@Configuration
class SwaggerConfig {

    @PostConstruct
    fun ignoreUserParameter() {
        SpringDocUtils
            .getConfig()
            .addAnnotationsToIgnore(LoginUser::class.java)
    }

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(components())
        .info(apiInfo())
        .addSecurityItem(securityItem())
        .addServersItem(Server().apply {
            this.url = "/"
        })

    private fun apiInfo() = Info()
        .title("I GOT IT :: API DOCS")
        .description("아가리 파이터들의 걸작 프로젝트")

    private fun components() = Components()
        .addSecuritySchemes(
            SECURITY_SCHEME_KEY,
            SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme(SECURITY_SCHEME)
                .bearerFormat(SECURITY_BEARER_FORMAT)
        )

    private fun securityItem() = SecurityRequirement()
        .addList(SECURITY_SCHEME_KEY)
}