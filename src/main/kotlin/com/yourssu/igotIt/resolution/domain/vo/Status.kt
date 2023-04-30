package com.yourssu.igotIt.resolution.domain.vo

enum class Status {
    INPROGRESS,
    DONE
    ;

    fun isDone(): Boolean {
        return this == DONE
    }

    fun isInProgress(): Boolean {
        return this == INPROGRESS
    }
}