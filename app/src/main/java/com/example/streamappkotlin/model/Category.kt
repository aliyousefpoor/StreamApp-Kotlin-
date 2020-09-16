package com.example.streamappkotlin.model

import com.example.streamappkotlin.utils.AppConstants
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
    private  var avatar: String? =null

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
    private var parent: Int = 0

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getAvatar(): String {
        return AppConstants.baseUrl + avatar
    }

    fun setAvatar(avatar: String) {
        this.avatar = avatar
    }

}