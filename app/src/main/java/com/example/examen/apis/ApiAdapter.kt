package com.example.examen.apis

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiAdapter {

    companion object {
        private var API_SERVICE: ApiService? = null

        fun getApiService(): ApiService? {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.networkInterceptors().add(Interceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.header("x-rapidapi-key", "d3fe3a34c5mshf70e1bb90c464a8p1ba4aajsn4233972e8a9c")
                requestBuilder.header("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                chain.proceed(requestBuilder.build())
            })

            val baseUrl = "https://community-open-weather-map.p.rapidapi.com/"

            val gson = GsonBuilder()
                .setLenient()
                .create()

            if (API_SERVICE == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build()
                API_SERVICE = retrofit.create(ApiService::class.java)
            }
            return API_SERVICE
        }
    }
}