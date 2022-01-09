package com.dtu.nemsport.models

object FakeDB {
    var listData = ArrayList<AktivitetData>()
    var kortNummberData = ArrayList<kortNumberData>()
    var userData = ArrayList<UserProfileData>()

    init {
        listData.add(AktivitetData("overskrift1", "10", "dato1", "note1"))
        kortNummberData.add(kortNumberData("", "","",""))
        userData.add(UserProfileData("navn1","email1","adresse1","nummer1"))
    }

}


