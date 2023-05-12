package com.yourssu.igotIt.resolution.domain.resolutionHistory

import com.yourssu.igotIt.common.domain.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "resolution_history")
class ResolutionHistory(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(nullable = false)
    val resolutionId: Long

) : BaseTimeEntity()