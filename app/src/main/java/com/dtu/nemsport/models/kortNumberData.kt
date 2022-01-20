package com.dtu.nemsport.models

import com.google.firebase.database.IgnoreExtraProperties
@IgnoreExtraProperties
data class kortNumberData (val kortNummer: String? = null, val MM: String? = null,
                           val YY: String? = null, val CVV: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot

}



