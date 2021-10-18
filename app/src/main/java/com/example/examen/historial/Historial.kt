package com.example.examen.historial

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.examen.helper.BDHelper
import com.example.examen.helper.ModelBitacora
import com.example.examen.historial.adapters.AdapterHistorial
import kotlinx.android.synthetic.main.fragment_historial2.*

/**
 * Muestra la bitacora de las peticiones realizadas
 */
class Historial : Fragment() {

    lateinit var recyclerBitacora: RecyclerView
    val mAdapter: AdapterHistorial = AdapterHistorial()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historial2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = BDHelper(context)
        val db: SQLiteDatabase = dbHelper.getWritableDatabase()

        val c: Cursor = db.rawQuery("SELECT id, latitud, longitud, fecha FROM bitacora", null)

        c.moveToFirst()
        val lista: ArrayList<ModelBitacora> = ArrayList()
        do {
            val id: Int = c.getInt(c.getColumnIndex("id"))
            val la: String = c.getString(c.getColumnIndex("latitud"))
            val lo: String = c.getString(c.getColumnIndex("longitud"))
            val fe: String = c.getString(c.getColumnIndex("fecha"))
            lista.add(ModelBitacora(id, lo, la, fe))
        } while (c.moveToNext())

        c.close()
        db.close()

        setDatos(lista)
    }

    fun setDatos(bitacora: ArrayList<ModelBitacora>) {
        recyclerBitacora = recyclerHistorial
        recyclerBitacora.setHasFixedSize(true)
        recyclerBitacora.layoutManager = LinearLayoutManager(context)
        context?.let { mAdapter.RecyclerAdapter(bitacora, it) }
        recyclerBitacora.adapter = mAdapter
    }
}