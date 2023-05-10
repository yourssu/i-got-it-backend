package com.yourssu.igotIt.resolution.domain.resolution.vo

enum class Status {
    INPROGRESS,
    DONE,
    NONE
    ;

    fun isDone(): Boolean {
        return this == DONE
    }

    fun isInProgress(): Boolean {
        return this == INPROGRESS
    }
}