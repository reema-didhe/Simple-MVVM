package com.example.mvvm.remote

import com.sample.mvvmlivedataroomdattbinding.models.UserModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    //@GET("list.json")
    @GET("/photos")
    fun getUsers(): Call<List<UserModel>>

}