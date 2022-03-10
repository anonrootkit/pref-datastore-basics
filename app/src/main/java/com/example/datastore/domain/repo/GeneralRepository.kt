package com.example.datastore.domain.repo

import android.content.Context
import com.example.datastore.data.preferences.PreferenceStorage

class GeneralRepository(
    private val preferenceStorage: PreferenceStorage
) {

    companion object{
        @Volatile
        private var INSTANCE : GeneralRepository? = null

        fun get(context: Context): GeneralRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = GeneralRepository(PreferenceStorage.get(context))
                INSTANCE!!
            }
        }

    }

    suspend fun storeName(name : String) = preferenceStorage.storeName(name)

    fun getName() = preferenceStorage.getName()
}