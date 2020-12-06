package com.example.examplefirebase.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class dataheroes (
    val idHero: String,
    val name : String,
    val sex: String


)