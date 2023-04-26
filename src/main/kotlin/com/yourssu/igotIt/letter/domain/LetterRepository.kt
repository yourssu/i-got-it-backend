package com.yourssu.igotIt.letter.domain

import org.springframework.data.jpa.repository.JpaRepository

interface LetterRepository : JpaRepository<Letter, Long> {
}