package com.example.streamappkotlin.model

data class UpdateProfile(
    var nickname: String?=null,
    var date_of_birth: String?=null,
    var gender: String?=null,
    var avatar: Avatar?=null
)
