package com.dtu.nemsport.models

object FakeDB {
    var listData = ArrayList<AktivitetData>()
    var kortNummberData = ArrayList<kortNumberData>()
    var userData = ArrayList<UserProfileData>()

    init {
        listData.add(AktivitetData("overskrift1", "spillere1", "dato1", "note1"))
        kortNummberData.add(kortNumberData("kortnummer1", "MM1","YY1","CVV1"))
        userData.add(UserProfileData("Jens Mortensen Eriksen","user@gmail.com","Nyhavn 23, 2000 Frederriksberg","481650361"))
    }

}


