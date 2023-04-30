package com.yourssu.igotIt.resolution.domain

import com.yourssu.igotIt.common.domain.BaseTimeEntity
import com.yourssu.igotIt.letter.domain.Letter
import com.yourssu.igotIt.resolution.domain.vo.Status
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

    @field:Enumerated(EnumType.STRING)
    var status: Status,

    @field:Column(nullable = true)
    val email: String? = null,

    @field:Column(nullable = false)
    val uniqueId: String,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "user_id")
    val user: User,

    @field:OneToMany(mappedBy = "resolution", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val letters: MutableList<Letter> = arrayListOf()

) : BaseTimeEntity() {

    fun updateStatus() {
        this.status = Status.DONE
    }
}