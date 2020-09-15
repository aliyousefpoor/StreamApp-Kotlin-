package com.example.streamappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class File {
    @SerializedName("id")
    @Expose
    private var id: Int = 0

    @SerializedName("name")
    @Expose
    private lateinit var name: String

    @SerializedName("file")
    @Expose
    private lateinit var file: String

    @SerializedName("length")
    @Expose
    private var length: Int = 0

    @SerializedName("file_redirect")
    @Expose
    private lateinit var fileRedirect: String
}