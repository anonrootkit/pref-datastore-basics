package com.example.datastore.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastore.utils.PreferenceConstants.PREFERENCE_NAME

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCE_NAME,
    produceMigrations = {
        listOf(SharedPreferencesMigration(it, PREFERENCE_NAME))
    }
)