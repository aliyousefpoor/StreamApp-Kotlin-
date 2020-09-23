package com.example.streamappkotlin.model

data class LoginStepTwoRequest(
    var mobile: String, var device_id: String,
    var verification_code: String
)