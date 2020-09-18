package com.example.streamappkotlin.model

data class User(
    var id: Int=0,
    var token: String="",
    var name: String="",
    var date: String="",
    var gender: String="",
    var avatar: String=""
)