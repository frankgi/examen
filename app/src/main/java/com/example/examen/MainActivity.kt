package com.example.examen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.examen.historial.Historial
import com.example.examen.views.Mapa
import com.example.examen.views.Request
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRquest.setOnClickListener(this)
        btnHistorial.setOnClickListener(this)
        btnMapa.setOnClickListener(this)

    }

    private fun newFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    private fun map(){

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            btnRquest.id -> newFragment(Request())
            btnHistorial.id -> newFragment(Historial())
            btnMapa.id -> newFragment(Mapa())
        }
    }
}