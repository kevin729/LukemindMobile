package com.professorperson.lmm

import android.app.Application

class BaseApplication : Application() {
    var isDarkMode = false

    fun toggleTheme() {
        isDarkMode = !isDarkMode
    }
}