package com.h.testapi.util

import android.content.Context
import android.content.SharedPreferences


object PrefsHelper  {

    const val TOKEN = "token"
    const val SESSION = "session"
    const val FIRST_SESSION = "first_session"
    private const val PREFS_NAME = "params"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
    }

    fun writeToken(token: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(TOKEN, token)
            commit()
        }
    }

    fun writeSession(session: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(SESSION, session)
            commit()
        }
    }

    fun readToken(): String {
        return prefs.getString(TOKEN, "")!!
    }

    fun readSession(): String {
        return prefs.getString(SESSION, "")!!
    }

    fun isFirstSession():Boolean {
        return prefs.getBoolean(FIRST_SESSION, true)
    }

    fun firstSession() {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putBoolean(FIRST_SESSION, false)
            commit()
        }
    }
}