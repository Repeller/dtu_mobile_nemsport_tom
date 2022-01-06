package com.dtu.nemsport.models

object FakeDB {
    var listData = ArrayList<AktivitetData>()
    var kortNummberData = ArrayList<kortNumberData>()
    var userData = ArrayList<UserProfileData>()

    init {
        listData.add(AktivitetData("overskrift1", "spillere1", "dato1", "note1"))
        kortNummberData.add(kortNumberData("", "","",""))
        userData.add(UserProfileData("","","",""))
    }

}


