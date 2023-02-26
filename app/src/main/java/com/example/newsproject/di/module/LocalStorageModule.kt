package com.example.newsproject.di.module

import android.content.Context
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class SharedPref @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)

    var themeMode: Int
        get() = prefs.getInt(THEME_MODE, -1)
        set(value) = prefs.edit().putInt(THEME_MODE, value).apply()


    private fun setField(key: String, value: Any?) {
        val editor = prefs.edit()

        if (value != null) {
            when (value) {
                is Boolean -> editor.putBoolean(key, value)
                is Float -> editor.putFloat(key, value)
                is Int -> editor.putInt(key, value)
                is Long -> editor.putLong(key, value)
                is String -> editor.putString(key, value)
                else -> throw IllegalArgumentException("This type not supported")
            }
            editor.apply()
        } else {
            editor.remove(key)
        }
    }

    companion object {
        private const val PREFS_FILE_NAME = "app.settings"
        private const val THEME_MODE = "theme_mode"

    }
}