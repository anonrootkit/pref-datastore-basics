package com.example.datastore.domain.repo

import android.content.Context
import com.example.datastore.data.preferences.PreferenceStorage
import com.example.datastore.utils.PreferenceConstants

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

    fun storeName(name : String) = preferenceStorage.storeName(name)

    fun getName() : String? = preferenceStorage.getName()

}