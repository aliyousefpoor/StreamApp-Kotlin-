package com.example.streamappkotlin.model


data class Store(
    var id: Int,
    var parent_categories: List<Category>,
    var name: String,
    var category: List<Category>,
    var tabStrip: List<Object>,
    var headeritem: List<Product>,
    var homeitem: List<HomeItem>
)