package com.example.examen.interfaces

import com.example.examen.apis.response.Cordenadas

interface ExamenViews {
    fun setResults(data: Cordenadas)
    fun setError()
    fun succesfull()
}