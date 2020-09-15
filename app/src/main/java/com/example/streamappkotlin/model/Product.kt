package com.example.streamappkotlin.model

import android.text.BoringLayout
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Product {
    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("name")
    @Expose
    private lateinit var name: String

    @SerializedName("name_english")
    @Expose
    private lateinit var nameEnglish: String

    @SerializedName("product_type")
    @Expose
    private var productType: Int = 0

    @SerializedName("producer_name")
    @Expose
    private lateinit var producerName: String

    @SerializedName("payment_type")
    @Expose
    private var paymentType: List<Object>? = null;

    @SerializedName("price")
    @Expose
    private var price: Int = 0

    @SerializedName("price_show")
    @Expose
    private lateinit var priceShow: Objects

    @SerializedName("avatar")
    @Expose
    private lateinit var avatar: Avatar

    @SerializedName("rank")
    @Expose
    private var rank: Double = 0.0

    @SerializedName("short_description")
    @Expose
    private lateinit var shortDescription: String

    @SerializedName("is_purchased")
    @Expose
    private var isPurchased: Boolean = false

    @SerializedName("comments")
    @Expose
    private var comments: Int = 0

    @SerializedName("files")
    @Expose
    private lateinit var file: List<File>

    @SerializedName("is_bookmarked")
    @Expose
    private var isBookmarked: Boolean = false

    @SerializedName("sku")
    @Expose
    private lateinit var sku: String

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getAvatar(): Avatar {
        return avatar
    }

    fun setId(avatar: Avatar) {
        this.avatar = avatar
    }

    fun getFile(): List<File> {
        return file
    }

    fun setId(file: List<File>) {
        this.file = file
    }
}