package com.professorperson.lmm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity
data class Note (
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "date") val date: String
) {
    constructor(): this(null, "", "", SimpleDateFormat("dd MMMM yyyy - HH:mm:ss", Locale.UK).format(Date()))
    constructor(title: String, text: String): this(null, title, text, SimpleDateFormat("dd MMMM yyyy - HH:mm:ss", Locale.UK).format(Date()))
}