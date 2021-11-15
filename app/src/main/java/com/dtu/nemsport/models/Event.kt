package com.dtu.nemsport.models
import java.sql.Date

data class Event(val id: Int,
                 val created: Date,
                 val title: String, val description: String,
                 val eventStart: Date,
                 val fk_time_id: Int,
                 val maxPlayers: Int,
                 val onlyPaid: Int,
                 val fk_member_id: Int) {

    override fun toString(): String {
        return "id: $id - " +
                "created: $created - " +
                "title: $title - " +
                "descript: $description - " +
                "Event start: $eventStart - " +
                "fk_time_id: $fk_time_id - " +
                "maxPlayers: $maxPlayers - " +
                "onlyPaid: $onlyPaid - " +
                "fk_member_id: $fk_member_id"
    }
}