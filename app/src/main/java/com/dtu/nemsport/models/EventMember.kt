package com.dtu.nemsport.models

data class EventMember(val id: Int,
                       val fk_member_id: Int,
                       val fk_event_id: Int) {

    override fun toString(): String {
        return  "id: $id - " +
                "fk_member_id: $fk_member_id - " +
                "fk_event_id: $fk_event_id"
    }
}
