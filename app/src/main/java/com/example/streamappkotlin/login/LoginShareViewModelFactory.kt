package com.example.streamappkotlin.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamappkotlin.repository.LoginRepository
import java.lang.IllegalArgumentException

class LoginShareViewModelFactory(private var loginRepository: LoginRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginShareViewModel::class.java)){
            return LoginShareViewModel(loginRepository) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}