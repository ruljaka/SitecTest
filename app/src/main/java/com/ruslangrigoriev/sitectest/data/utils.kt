package com.ruslangrigoriev.sitectest.data

import android.content.Context


const val IMEI = "IMEI"
const val SHARED_PREF: String = "sharedPref"

fun Context.saveIMEI(imei: String) {
    val sharedPref = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putString(IMEI, imei)
    editor.apply()
}

fun Context.getIMEI(): String {
    val sharedPref = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    sharedPref.getString(IMEI, "")?.let { imei ->
        return imei
    } ?: throw IllegalStateException("IMEI not set")

}