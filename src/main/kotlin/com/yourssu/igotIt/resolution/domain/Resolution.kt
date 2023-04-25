package com.yourssu.igotIt.resolution.domain

import com.yourssu.igotIt.common.domain.BaseTimeEntity
import com.yourssu.igotIt.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "resolution")
class Resolution (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(nullable = false)
    val period: Int,

    @field:Column(nullable = false)
    val content: String,

    @field:Column(nullable = true)
    val email: String? = null,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "user_id")
    val user: User

) : BaseTimeEntity()