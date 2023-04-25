package com.yourssu.igotIt.common.handler

import com.yourssu.igotIt.common.annotation.LoginUser
import com.yourssu.igotIt.user.domain.User
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class LoginUserArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val isLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser::class.java)
        val isUserClass = User::class.java.isAssignableFrom(parameter.parameterType)

        return isLoginUserAnnotation && isUserClass
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        with(SecurityContextHolder.getContext()) {
            if (authentication.principal != null && authentication.principal is User) {
                return authentication.principal as User
            }else throw RuntimeException("Authentication의 principal 객체가 User 타입이 아닙니다.")
        }
    }
}