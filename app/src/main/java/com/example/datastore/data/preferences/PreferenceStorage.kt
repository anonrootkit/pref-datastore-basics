package com.example.datastore.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.datastore.utils.PreferenceConstants.NAME_KEY
import com.example.datastore.utils.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceStorage(private val dataStore : DataStore<Preferences>) {

    companion object{
        @Volatile
        private var INSTANCE : PreferenceStorage? = null

        fun get(context: Context): PreferenceStorage {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = PreferenceStorage(dataStore = context.dataStore)
                INSTANCE!!
            }
        }

    }

    suspend fun storeName(name : String) {
        dataStore.edit { it[NAME_KEY] = name }
    }

    fun getName(): Flow<String?> = dataStore.data.map { it[NAME_KEY] }
}