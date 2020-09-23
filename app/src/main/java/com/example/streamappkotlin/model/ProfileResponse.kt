package com.example.streamappkotlin.model


data class ProfileResponse(
    val credit: Credit,
    val magic_credit: Credit,
    val id: Int,
    val error: String,
    val nickname: String,
    val date_of_birth: String,
    val gender: String,
    val avatar: String,
    val mobile: String,
    val email: String,
    val has_supercollection: String,
    val ispData: IspData,
    val token: String
)
