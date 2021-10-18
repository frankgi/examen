package com.example.examen.historial.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R
import com.example.examen.helper.ModelBitacora
import kotlinx.android.synthetic.main.layout_historial.view.*

class AdapterHistorial : RecyclerView.Adapter<AdapterHistorial.ViewHolder>() {

    var bitacora: List<ModelBitacora> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(bitacora: ArrayList<ModelBitacora>, context: Context) {
        this.bitacora = bitacora
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = bitacora.get(position)
        holder.bind(item, context, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.layout_historial, parent, false))
    }

    override fun getItemCount(): Int {
        return bitacora.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val id = view.num
        val lat = view.latitud
        val lon = view.longitud
        val date = view.fecha
        val card = view.cardContainer

        fun bind(value: ModelBitacora, context: Context, position: Int) {

            id.text = value.id.toString()
            lat.text = value.lat
            lon.text = value.lon
            date.text = value.fecha

            card.setBackgroundResource(
                if (position % 2 == 0) R.color.gray
                else R.color.white
            )
        }
    }
}