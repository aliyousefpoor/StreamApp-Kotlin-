package com.example.streamappkotlin.login

import com.example.streamappkotlin.model.User

interface LoginStepTwoListener {
    fun userExist(user: User)
}