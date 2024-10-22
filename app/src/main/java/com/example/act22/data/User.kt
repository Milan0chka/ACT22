package com.example.act22.data

data class User (
    var ID: Int,
    var firstName : String,
    var secondName : String,
    var age : Int,
    var email : String,
    var passpord : String,
    val portfolio : Portfolio
)