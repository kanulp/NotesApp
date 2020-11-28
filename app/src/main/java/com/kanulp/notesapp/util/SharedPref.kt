package com.kanulp.notesapp.util

import android.content.Context
import android.content.SharedPreferences

class SharedPref  {
    object SharedPref {
        private const val APP_SETTINGS = "SharedPrefs"

        // properties
        private const val NOTESDATA = "Notes"
        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
        }

        fun getData(context: Context): String? {
            return getSharedPreferences(context).getString(NOTESDATA, null)
        }

        fun setData(context: Context, newValue: String?) {
            val editor = getSharedPreferences(context).edit()
            editor.putString(NOTESDATA, newValue)
            editor.commit()
        }
    }
}