package com.yourssu.igotIt.letter.domain

import com.yourssu.igotIt.common.domain.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "letter")
class Letter(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(nullable = false)
    val nickname: String,

    @field:Column(nullable = false)
    val content: String
) : BaseTimeEntity()