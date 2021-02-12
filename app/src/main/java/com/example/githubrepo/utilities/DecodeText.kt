package com.example.githubrepo.utilities

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class DecodeText {
    fun decodeString(encoded: String): String {
        val dataDec = Base64.decode(encoded, Base64.DEFAULT)
        var decodedString = ""
        try {
            decodedString = String(dataDec, Charset.forName("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } finally {
            return decodedString
        }
    }
}