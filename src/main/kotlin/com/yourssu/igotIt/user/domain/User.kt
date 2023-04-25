package com.yourssu.igotIt.user.domain

import com.yourssu.igotIt.common.domain.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "users")
class User (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(nullable = false, unique = true)
    val email: String,

    @field:Column(nullable = true)
    var nickname: String? = null

) : BaseTimeEntity() {

    fun updateNickname(nickname: String) {
        this.nickname = nickname
    }
}