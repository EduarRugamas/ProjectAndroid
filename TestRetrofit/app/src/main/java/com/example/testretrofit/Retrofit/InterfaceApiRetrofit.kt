package com.example.testretrofit.Retrofit

import retrofit2.Call
import retrofit2.http.GET

interface InterfaceApiRetrofit {

    @GET("people/10/")
    fun GetAllResponse(): Call<List<StarWarsApiItem>>
}