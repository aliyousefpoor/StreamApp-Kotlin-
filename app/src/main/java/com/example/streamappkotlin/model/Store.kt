package com.example.streamappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Store {
    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("parent_categories")
    @Expose
    private lateinit var parentCategory: List<Category>

    @SerializedName("category")
    @Expose
    private lateinit var category: List<Category>

    @SerializedName("tabStrip")
    @Expose
    private lateinit var tabStrip: List<Object>

    @SerializedName("headeritem")
    @Expose
    private lateinit var headerItem: List<Product>

    @SerializedName("homeitem")
    @Expose
    private lateinit var homeItem: List<HomeItem>

    fun getId(): Int {
        return id
    }
    fun setId(id:Int){
        this.id=id
    }
    fun getCategory(): List<Category> {
        return category
    }
    fun setCategory(parentCategory: List<Category>){
        this.category=category
    }
    fun getHeaderItem(): List<Product> {
        return headerItem
    }
    fun setHeaderItem(headerItem:List<Product>){
        this.headerItem=headerItem
    }
    fun getHomeItem(): List<HomeItem> {
        return homeItem
    }
    fun setHomeItem(homeItem:List<HomeItem>){
        this.homeItem=homeItem
    }
}