package com.example.streamappkotlin.utils

import android.os.Build
import android.text.TextUtils

class AppConstants {
    companion object {
        const val baseUrl: String = "https://api.vasapi.click/"
        var deviceModel: String = android.os.Build.MODEL

//        fun getDeviceName(): String {
//            val manufacturer: String = Build.MANUFACTURER
//            val model: String = Build.MODEL
//            if (model.startsWith(manufacturer)) {
//                return capitalize(model)
//            }
//            return capitalize(manufacturer) + " " + model
//        }
//
//        private fun capitalize(string: String): String {
//            if (TextUtils.isEmpty(string)) {
//                return string
//            }
//            val arr: CharArray = string.toCharArray()
//            var capitalizeNext: Boolean = true
//            val phrase = java.lang.StringBuilder()
//            for (char: Char in arr) {
//                if (capitalizeNext && Char.isLetter(char)) {
//                    phrase.append(Char.toUpperCase(char))
//                    capitalizeNext = false
//
//                }
//                phrase.append(char)
//            }
//            return phrase.toString()
//        }

        fun getAndroidVersion(): String {
            val release: String = Build.VERSION.RELEASE
            val sdkVersion: Int = Build.VERSION.SDK_INT
            return "Android$sdkVersion($release)"

        }
    }
}


private fun Char.Companion.toUpperCase(char: Char) {

}





