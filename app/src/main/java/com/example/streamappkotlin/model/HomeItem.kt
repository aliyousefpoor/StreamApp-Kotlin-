package com.example.streamappkotlin.model


data class HomeItem(
    var id: Int,
    var title: String,
    var sub_title: String,
    var position: Int,
    var module: Int,
    var row_type: String,
    var products:List<Product>,
    var row_mode:Int
)