package com.example.streamappkotlin.model

import android.os.Parcel
import android.os.Parcelable

 class User(
     var id: Int=0,
     var token: String? ="",
     var name: String? ="",
     var date: String?="",
     var gender: String?="",
     var avatar: String?=""
):Parcelable {
     constructor(parcel: Parcel) : this(
         parcel.readInt(),
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readString()
     ) {
     }

     override fun writeToParcel(dest: Parcel?, flags: Int) {
         TODO("Not yet implemented")
     }

     override fun describeContents(): Int {
         TODO("Not yet implemented")
     }

     companion object CREATOR : Parcelable.Creator<User> {
         override fun createFromParcel(parcel: Parcel): User {
             return User(parcel)
         }

         override fun newArray(size: Int): Array<User?> {
             return arrayOfNulls(size)
         }
     }
 }