package com.yourssu.igotIt.resolution.dto

import com.yourssu.igotIt.resolution.domain.resolution.vo.Status

data class ResolutionGetResponse(
    val userId: Long,
    val nickname: String,
    val content: String,
    val dday: Int,
    val status: Status,
    val isDeleted: Boolean,
) {
    companion object {
        fun generateEmpty(): ResolutionGetResponse {
            return ResolutionGetResponse(
                userId = -1,
                nickname = "",
                content = "",
                dday = -1,
                status = Status.NONE,
                isDeleted = true
            )
        }
    }
}