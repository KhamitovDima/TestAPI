package com.h.testapi.util

import android.icu.util.LocaleData
import java.util.*
import java.text.SimpleDateFormat

object DateUtils {

    private val myLocale = Locale("ru", "RU")

    @JvmStatic
    fun convertDate(digits: Long): String {
        return SimpleDateFormat("dd-MM-yyyy HH:mm:ss", myLocale)
            .format(Date(digits.toInt() * 1000L))
    }
}
