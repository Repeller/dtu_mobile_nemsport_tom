package com.dtu.nemsport.models

import java.sql.Date

data class Time(val id: Int,
                val timeStart: Date,
                val timeEnd: Date) {

    override fun toString(): String {
        return "id: $id - " +
                "timeStart: $timeStart - " +
                "timeEnd: $timeEnd"
    }
}
