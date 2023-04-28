package com.yourssu.igotIt.mail.domain

interface MailClient {
    fun generateRequest(): List<MailRequest>
}