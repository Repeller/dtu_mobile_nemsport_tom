package com.dtu.nemsport.models

object FakeDB {
    var listData = ArrayList<AktivitetData>()
    var userData = ArrayList<UserProfileData>()

    init {
        listData.add(AktivitetData("overskrift1", "spillere1", "dato1", "note1"))
        userData.add(UserProfileData("navn1","email1","adresse1","nummer1"))
    }

}


