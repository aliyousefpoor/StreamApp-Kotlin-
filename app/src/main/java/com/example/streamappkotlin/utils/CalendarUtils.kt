package com.example.streamappkotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import com.example.streamappkotlin.R
import com.example.streamappkotlin.profile.DateListener
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar

class CalendarUtils(private var context: Context, private var dateListener: DateListener) {
    private lateinit var picker: PersianDatePickerDialog

    @SuppressLint("ResourceAsColor")
    fun showCalendar() {
        val initDate = PersianCalendar()
        initDate.setPersianDate(1370, 3, 13)
        picker = PersianDatePickerDialog(context)
            .setPositiveButtonString("باشه")
            .setNegativeButton("بیخیال")
            .setTodayButton("امروز")
            .setTodayButtonVisible(true)
            .setMinYear(1300)
            .setMaxYear(1420)
            .setActionTextColor(R.color.white)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setActionTextColor(Color.GRAY)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(object : Listener {
                override fun onDateSelected(persianCalendar: PersianCalendar) {
                    val persianDate: String =
                        persianCalendar.persianShortDate.replace("/".toRegex(), "-")
//                            .replace("0".toRegex(), "۰")
//                            .replace("1".toRegex(), "۱")
//                            .replace("2".toRegex(), "۲")
//                            .replace("3".toRegex(), "۳")
//                            .replace("4".toRegex(), "۴")
//                            .replace("5".toRegex(), "۵")
//                            .replace("6".toRegex(), "۶")
//                            .replace("7".toRegex(), "۷")
//                            .replace("8".toRegex(), "۸")
//                            .replace("9".toRegex(), "۹")

                    dateListener.onDateChange(persianDate)
                }

                override fun onDismissed() {

                }

            })
        picker.show()
    }
}

