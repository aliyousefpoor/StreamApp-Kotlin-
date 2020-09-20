package com.example.streamappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comment(
    var id: Int,
    var title: String,
    var score: Int,
    var comment_text: String,
    var likes: Int,
    var dislikes: Int,
    var user: String,
    var user_avatar: String,
    var product: Int,
    var is_read: Boolean,
    var is_approved: Boolean,
    var product_name: String,
    var date_modify: String

)