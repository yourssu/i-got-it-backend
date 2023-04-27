package com.yourssu.igotIt.letter.domain

import com.yourssu.igotIt.resolution.domain.Resolution
import org.springframework.data.jpa.repository.JpaRepository

interface LetterRepository : JpaRepository<Letter, Long> {
    fun findAllByResolution(resolution: Resolution): List<Letter>
}