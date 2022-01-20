package com.dtu.nemsport.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val mail: String? = null, val name: String? = null, val phone:
String? = null, val address: String? = null, val member: Boolean? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}