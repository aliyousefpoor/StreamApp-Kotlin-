package com.example.streamappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Avatar {
    @SerializedName("xxxdpi")
    @Expose
    private lateinit var xxxdpi:String
    @SerializedName("xxhdpi")
    @Expose
    private lateinit var xxhdpi:String
    @SerializedName("xhdpi")
    @Expose
    private lateinit var xhdpi:String
    @SerializedName("hdpi")
    @Expose
    private lateinit var hdpi:String
    @SerializedName("mdpi")
    @Expose
    private lateinit var mdpi:String
}