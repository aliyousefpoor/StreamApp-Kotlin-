package com.example.streamappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeItem {
    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("title")
    @Expose
    private lateinit var title: String

    @SerializedName("sub_title")
    @Expose
    private lateinit var subTitle: String

    @SerializedName("position")
    @Expose
    private var position: Int = 0

    @SerializedName("module")
    @Expose
    private var module: Int = 0

    @SerializedName("row_type")
    @Expose
    private lateinit var rowType: String

    @SerializedName("products")
    @Expose
    private lateinit var products: List<Product>

    @SerializedName("row_mode")
    @Expose
    private var rowMode: Int = 0
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

    fun getProducts(): List<Product> {
        return products
    }

    fun setProducts(products: List<Product>) {
        this.products = products
    }
}