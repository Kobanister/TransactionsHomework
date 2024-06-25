package com.transactions.homework.util.datetime

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Locale

open class DateFormatter(protected val timePattern: String) {

    private val timeFormatter: DateTimeFormatter by lazy {
        DateTimeFormatter.ofPattern(timePattern, Locale.getDefault())
    }

    open fun formatTime(date: TemporalAccessor?): String = timeFormatter.format(date)
    open fun parseTime(time: String): ZonedDateTime = ZonedDateTime.parse(time).withZoneSameInstant(ZoneId.systemDefault())
}
