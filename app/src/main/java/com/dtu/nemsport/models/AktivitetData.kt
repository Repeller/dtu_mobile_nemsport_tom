package com.dtu.nemsport.models

import com.google.firebase.Timestamp
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AktivitetData(
    var id : String? = null,
    var title: String? = null,
    var made_by: String? = null,
    var max_players: Long? = null,
    var joined_amount: Long? = null,
    var date: Timestamp? = null,
    var note: String? = null,
    )