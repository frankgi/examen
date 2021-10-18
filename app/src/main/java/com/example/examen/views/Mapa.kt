package com.example.examen.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.examen.R
import com.example.examen.apis.response.Cordenadas
import com.example.examen.interfaces.ExamenPresenters
import com.example.examen.interfaces.ExamenViews
import com.example.examen.presenters.ExamenPresenterImpl
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Francisco Garcia
 * Fragment que muestra un mapa y enfoca el marcador segun las
 * cordenadas obtenidas
 */
class Mapa : Fragment(), ExamenViews, OnMapReadyCallback {

    var lat: Double = 0.0
    var lon: Double = 0.0
    private lateinit var presenters: ExamenPresenters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenters = ExamenPresenterImpl(this)

        presenters.getDatos()
    }

    override fun setResults(data: Cordenadas) {
        lat = data.coord[0].lat.toDouble()
        lon = data.coord[0].lon.toDouble()

        val fragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        fragment!!.getMapAsync(this)

    }

    override fun setError() {
        Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun succesfull() {
        Toast.makeText(context, getString(R.string.successfull), Toast.LENGTH_SHORT).show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val ubicacion = LatLng(lat, lon)
        googleMap.addMarker(
            MarkerOptions()
                .position(ubicacion)
                .title("Marker")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 10f))
    }


}