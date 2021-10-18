package com.example.examen.interfaces

import com.example.examen.apis.response.Cordenadas

interface ExamenPresenters {
    fun getDatos()
    fun setResults(data: String)
    fun setError()
}