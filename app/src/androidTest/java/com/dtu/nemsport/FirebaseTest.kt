package com.dtu.nemsport

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import com.dtu.nemsport.models.dbHelper
import com.dtu.nemsport.view.fragments.loginFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class FirebaseTest {

//    private lateinit var database: FirebaseFirestore
//    private lateinit var auth: FirebaseAuth
    private lateinit var dbHelp : dbHelper

    // this get run before every test
    @Before
    fun setup() {
        // TODO: get this to work with mock database
        // dbHelp = dbHelper(Mockito.mock(FirebaseFirestore.class), )
        dbHelp = dbHelper(FirebaseFirestore.getInstance(), FirebaseAuth.getInstance())
    }

    @Test
    @LargeTest
    fun login() {

        var checking: Boolean

        // var fact : FragmentActivity
        // fact = FragmentFactory.loadFragmentClass("", "loginFragment")
        // https://stackoverflow.com/questions/46458735/instrumented-unit-class-test-cant-create-handler-inside-thread-that-has-not-c
        // https://developer.android.com/guide/fragments/test'
        // https://medium.com/android-news/testing-fragment-in-isolation-with-fragmentfactory-d91c47ef6ed4
        runBlocking {
            InstrumentationRegistry.getInstrumentation().runOnMainSync(Runnable {
                val context = InstrumentationRegistry.getInstrumentation().targetContext

                runBlocking {
                    checking = dbHelp.login(FragmentActivity(), "tes@tes.dk", "nemsport")


                }

                assertEquals(checking, true)
            })
        }


    }

    @Test
    @LargeTest
    fun saveSharedPref() {

        var checking: Boolean

        runBlocking {
            checking = dbHelp.saveToSharedPref(FragmentActivity(), "nemsport_uid", "thisIsMyUserId")
        }

        assertEquals(checking, true)
    }
}

