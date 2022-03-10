package com.example.datastore.utils

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceConstants{
    const val PREFERENCE_NAME = "dataStore"
    private const val NAME_KEY_LABEL = "name"

    val NAME_KEY = stringPreferencesKey(NAME_KEY_LABEL)
}