package com.example.streamappkotlin.model


data class Category(
    var id: Int,
    var title: String,
    var avatar: String,
    var position: Int,
    var is_default: Boolean,
    var is_enable: Boolean,
    var is_visible: Boolean,
    var parent: Int
)
