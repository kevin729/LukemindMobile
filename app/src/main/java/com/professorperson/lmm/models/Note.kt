package com.professorperson.lmm.models

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Note (
    val title: String,
    val text: String,
    val date: String
) {
    constructor(title: String, text: String): this(title, text, SimpleDateFormat("dd MMMM yyyy - HH:mm:ss", Locale.UK).format(Date()))
}