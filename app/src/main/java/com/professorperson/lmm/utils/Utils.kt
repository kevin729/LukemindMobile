package com.professorperson.lmm.utils

import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

fun httpCall(_url : String) : String {

    var response = ""
    val callThread = Thread {


        val url = URL(_url)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        if (connection.responseCode != 200) {
            println("Error response code: " + connection.responseMessage)
        }

        val reader = BufferedReader(connection.inputStream.reader());
        var line = reader.readLine()

        while (!line.isNullOrEmpty()) {
            response += line
            line = reader.readLine()
        }
    }
    callThread.start()
    callThread.join()


    return response
}