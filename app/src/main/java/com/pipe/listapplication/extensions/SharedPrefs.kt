package com.pipe.listapplication.extensions

import android.content.Context
import android.content.SharedPreferences
import com.pipe.listapplication.BuildConfig

class SharedPrefs(context: Context) {

    companion object {
        private const val PREF = BuildConfig.APPLICATION_ID
        private const val AFTER = "THIS_AFTER"

    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)


    fun setAfter(page: Int) {put(AFTER, page)}

    fun clearAfter() {put(AFTER, 0)}

    fun getAfter() = get(AFTER, Int::class.java)


    private fun <T> get(key: String, clazz: Class<T>) =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, 0.0f)
            Double::class.java -> sharedPref.getFloat(key, 0.0f)
            Int::class.java -> sharedPref.getInt(key, 0)
            Long::class.java -> sharedPref.getLong(key, 0)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()

        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }
}