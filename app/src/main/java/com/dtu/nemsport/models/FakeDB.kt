package com.dtu.nemsport.models

object FakeDB {
    var listData = ArrayList<AktivitetData>()
    var userData = ArrayList<UserProfileData>()

    init {
        listData.add(AktivitetData("overskrift1", "spillere1", "dato1", "note1"))
        userData.add(UserProfileData("Jens Mortensen","user@gmail.com","Nyhavn 23, 2000 Frederriksberg","481650361"))
    }

}


