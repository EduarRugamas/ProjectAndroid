package com.example.apidogretrofit.Retrofit

import com.example.apidogretrofit.Dog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part

interface DogInterfaces {
    @GET("boxer/images/random/2/alt")
    fun ListDogs() : Call<List<Dog>>
}