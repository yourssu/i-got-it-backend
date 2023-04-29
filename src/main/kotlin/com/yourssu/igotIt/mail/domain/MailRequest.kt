package com.yourssu.igotIt.mail.domain

import org.thymeleaf.context.Context

interface MailRequest {
    fun email(): String
    fun subject(): String
    fun type(): String
    fun createContext(baseUrl: String): Context
}