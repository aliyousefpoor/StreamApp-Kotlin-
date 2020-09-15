package com.example.streamappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Category {
    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("is_default")
    @Expose
    private var isDefault: Boolean = false

    @SerializedName("title")
    @Expose
    private lateinit var title: String

    @SerializedName("avatar")
    @Expose
    private lateinit var avatar: String

    @SerializedName("position")
    @Expose
    private var position: Int = 0

    @SerializedName("is_enable")
    @Expose
    private var isEnable: Boolean = false

    @SerializedName("is_visible")
    @Expose
    private var isVisible: Boolean = false

    @SerializedName("parent")
    @Expose
    private  var parent: Int = 0

}