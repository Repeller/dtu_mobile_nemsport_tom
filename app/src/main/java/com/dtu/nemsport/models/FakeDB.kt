package com.dtu.nemsport.models

object FakeDB {
    var listData = ArrayList<AktivitetData>()

    init {
        listData.add(AktivitetData("overskrift1", "spillere1", "dato1", "note1"))
    }




}