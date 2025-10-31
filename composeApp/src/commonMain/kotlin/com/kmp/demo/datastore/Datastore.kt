package com.kmp.demo.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import okio.Path

fun createDataStore(): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
        migrations = emptyList(),
        produceFile = { producePath() }
    )

expect fun producePath(): Path

internal const val dataStoreFileName = "dice.preferences_pb"