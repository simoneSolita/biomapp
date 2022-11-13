package com.goblin.consulting.biomapp.utils

import java.text.SimpleDateFormat
import java.util.*

data class Time(
    val day : Int,
    val month : Int,
    val year : Int
)

fun getDate(day : Int, month : Int, year : Int) : Date
{
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, day)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.time
}

fun Date.toCalendar() : Time
{
    val calendar = Calendar.getInstance()
    calendar.time = this

    return Time(
        calendar.get(Calendar.DAY_OF_MONTH),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.YEAR)
    )
}

fun Date.toFormattedString(format : String = "yyyy-MM-dd") : String
{
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}