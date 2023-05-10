package com.yourssu.igotIt.letter.domain

import com.yourssu.igotIt.common.domain.BaseTimeEntity
import com.yourssu.igotIt.resolution.domain.resolution.Resolution
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
    val content: String,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "resolution_id")
    val resolution: Resolution

) : BaseTimeEntity()