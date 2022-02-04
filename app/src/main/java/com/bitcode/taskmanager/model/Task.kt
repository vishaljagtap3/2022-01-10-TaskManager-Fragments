package com.bitcode.taskmanager.model

data class Task(
    var title : String,
    var state : Int = STATE_PENDING
) {
    var id : Int = nextTaskId++

    companion object {
        var nextTaskId = 1

        val STATE_DONE = 1
        val STATE_PENDING = 2
        val STATE_ACTIVE = 3
    }
}