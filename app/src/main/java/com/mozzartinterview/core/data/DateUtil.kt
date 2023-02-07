package com.mozzartinterview.core.data

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toDrawEndString(): String {
    val instant = Instant.ofEpochMilli(this)
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return instant.atZone(ZoneId.systemDefault()).format(formatter)
}

//TODO: This logic should go to VM so it is not run on UiThread
fun Long.toRemaningTimeString(): String {
    val minutes = this / 1000 / 60
    val seconds = (this / 1000) % 60
    return "$minutes:$seconds"
}

fun getCurrentTimeAsString(): String {
    val instant = Instant.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return instant.atZone(ZoneId.systemDefault()).format(formatter)

}