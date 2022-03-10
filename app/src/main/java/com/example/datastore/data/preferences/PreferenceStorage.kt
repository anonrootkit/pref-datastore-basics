package com.example.datastore.data.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.datastore.utils.PreferenceConstants.NAME_KEY
import com.example.datastore.utils.PreferenceConstants.PREFERENCE_NAME

class PreferenceStorage(private val sharedPreferences: SharedPreferences) {

    companion object{

        @Volatile
        private var INSTANCE : PreferenceStorage? = null

        fun get(context: Context): PreferenceStorage {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = PreferenceStorage(sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE))
                INSTANCE!!
            }
        }

    }

    fun storeName(name : String) {
        sharedPreferences.edit().putString(NAME_KEY, name).apply()
    }

    fun getName() : String? {
        return sharedPreferences.getString(NAME_KEY, null)
    }
}