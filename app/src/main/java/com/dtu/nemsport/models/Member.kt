package com.dtu.nemsport.models

data class Member(val id:Int,
                  val username: String,
                  val password: String,
                  val memberType: String) {

    override fun toString(): String {
        return "id: $id - " +
                "username: $username - " +
                "memberType: $memberType"
    }
}
