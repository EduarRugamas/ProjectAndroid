package com.example.apidogretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dog(
    val message: List<Message>

    )

data class Message(
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("altText")
    val altText: String

)

/*
* class PokeApi : ArrayList<StarWarsApiItem>()

     data class StarWarsApiItem (
         @SerializedName("name")
         @Expose
         val name: String,
         @SerializedName("height")
         @Expose
         val height: String
     )
     *
     *
data class Dog(
    val message: List<Message>,

)

data class Message(
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("altText")
    val altText: String

)
* */