package com.dtu.nemsport.view.fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dtu.nemsport.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class banerFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        // Pointer til fælleparken
        val faelleparken = LatLng(55.70023740964454, 12.5673661958115)
        googleMap.addMarker(MarkerOptions().position(faelleparken).title("Baner ved fælleparken"))
        googleMap.setMinZoomPreference(12f)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(faelleparken))

        // pointer til lyngby
        val lyngby = LatLng( 55.71886809884253, 12.561562056242423)
        googleMap.addMarker(MarkerOptions().position(lyngby).title("Baner ved Ryparkens Idrætsanlæg"))
        googleMap.setMinZoomPreference(12f)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(lyngby))


        // pointer til Lundehusskolen
        val lundehusskolen = LatLng( 55.71843332755004, 12.544445669732541)
        googleMap.addMarker(MarkerOptions().position(lundehusskolen).title("Baner ved Lundehusskolen"))
        googleMap.setMinZoomPreference(12f)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(lundehusskolen))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_baner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}