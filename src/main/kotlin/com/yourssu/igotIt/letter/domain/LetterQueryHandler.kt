package com.yourssu.igotIt.letter.domain

interface LetterQueryHandler {
    fun findById(id: Long): Letter
}