package com.example.testretrofit.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokeApi : ArrayList<StarWarsApiItem>()

     data class StarWarsApiItem (
         @SerializedName("name")
         @Expose
         val name: String,
         @SerializedName("height")
         @Expose
         val height: String
     )
