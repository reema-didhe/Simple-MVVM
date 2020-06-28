package com.example.mvvm.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private lateinit var retrofit: Retrofit

    //const val baseUrl = "https://raw.githubusercontent.com/granulartechnical/takehome-mobile/master/"
    const val baseUrl = "https://jsonplaceholder.typicode.com"

    fun getInstance(): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()


        /*retrofit client to get JSON data from URL*/
        retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            ).build()

        return retrofit;
    }
}

