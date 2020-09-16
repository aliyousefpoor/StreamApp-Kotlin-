package com.example.streamappkotlin.model

data class Category(var id:Int,var title:String,var avatar:String,var position:Int,var isDefault: Boolean,var isEnable: Boolean
,var parent: Int)
