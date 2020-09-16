package com.example.streamappkotlin.model

data class LoginStepTwoResponse(
    var user_id: Int, var token: String, var message: String
    , var nickname: String, var fino_token: String
)