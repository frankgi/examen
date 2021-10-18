package com.example.examen.model

import android.util.Log
import com.example.examen.apis.ApiAdapter
import com.example.examen.interfaces.ExamenModel
import com.example.examen.presenters.ExamenPresenterImpl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class ExamenModelImpl(var presenter: ExamenPresenterImpl) : ExamenModel {

    override fun getDatos() {
        var data = ""

        val datos: Call<ResponseBody>? =
            ApiAdapter.getApiService()?.getData()
        datos?.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                presenter.setError()
            }

            override fun onResponse(
                call: Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    data = response.body()?.string().toString()
                    presenter.setResults(data)
                } else {
                    presenter.setError()
                }
            }
        })
    }
}