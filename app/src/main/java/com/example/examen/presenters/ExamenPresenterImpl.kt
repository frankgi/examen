package com.example.examen.presenters

import android.util.Log
import com.example.examen.apis.response.Cordenadas
import com.example.examen.model.ExamenModelImpl
import com.example.examen.interfaces.ExamenModel
import com.example.examen.interfaces.ExamenPresenters
import com.example.examen.interfaces.ExamenViews
import com.google.gson.Gson

class ExamenPresenterImpl(var view: ExamenViews) : ExamenPresenters {

    var model: ExamenModel = ExamenModelImpl(this)

    override fun getDatos() {
        model.getDatos()
    }

    override fun setResults(data: String) {
        view.succesfull()
        Log.d("TAG", "setResults: "+data)
        val newBody = data.toString().replace("test(", "").replace(")", "")
            .replace("{", "[{").replace("}", "}]")
        val json = newBody.substring(1, newBody.length - 1);
        val gson = Gson()
        val cordenadas: Cordenadas = gson.fromJson(json, Cordenadas::class.java)
        view.setResults(cordenadas)
    }

    override fun setError() {
        view.setError()
    }

}