package com.example.streamappkotlin.model


data class Credit(
    val gem: Int,
    val coin: Int,
    val cash: Int,
    val price_unit: String = "rial")