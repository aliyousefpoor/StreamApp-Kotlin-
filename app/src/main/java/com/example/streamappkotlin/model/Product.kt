package com.example.streamappkotlin.model

import java.util.*

data class Product(
    var id: Int,
    var name: String,
    var name_english: String,
    var product_type: Int,
    var producer_name: String,
    var payment_type: List<Object>,
    var price: Int,
    var price_show: Objects,
    var avatar: Avatar,
    var rank: Double,
    var short_description: String,
    var is_purchased: Boolean,
    var comments: Int,
    var files: List<File>,
    var is_bookmarked: Boolean,
    var price_unit: String,
    var total_view: Int,
    var date_added: String,
    var invest_goal: String,
    var product_staff: List<Objects>,
    var support: Support,
    var is_special: Boolean,
    var additional_attributes: List<Objects>,
    var date_published: String,
    var customjson: String,
    var approved_age: String
)