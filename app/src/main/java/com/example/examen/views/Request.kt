package com.example.examen.views

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.examen.R
import com.example.examen.apis.response.Cordenadas
import com.example.examen.helper.BDHelper
import com.example.examen.interfaces.ExamenPresenters
import com.example.examen.interfaces.ExamenViews
import com.example.examen.presenters.ExamenPresenterImpl
import kotlinx.android.synthetic.main.fragment_request.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Francisco Garcia
 * Consulta los datos del api indicada
 */
class Request : Fragment(), ExamenViews {

    private lateinit var presenters: ExamenPresenters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenters = ExamenPresenterImpl(this)
        return inflater.inflate(R.layout.fragment_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenters.getDatos()
    }

    override fun setResults(data: Cordenadas) {
        addBitacora(data)
    }

    override fun setError() {
        txt.setText(getString(R.string.error))
    }

    override fun succesfull() {
        txt.text = getString(R.string.successfull)
    }

    fun addBitacora(data: Cordenadas) {
        val c: Calendar = Calendar.getInstance()
        val df = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US)
        val formattedDate = df.format(c.time)

        val dbHelper = BDHelper(context)
        val db: SQLiteDatabase = dbHelper.getWritableDatabase()
        var cv = ContentValues()
        cv.put("latitud", data.coord[0].lat.toString())
        cv.put("longitud", data.coord[0].lon.toString())
        cv.put("fecha", formattedDate)
        val res = db.insert("bitacora", null, cv)
        Log.d("TAG", "addBitacora: " + res)
    }
}



