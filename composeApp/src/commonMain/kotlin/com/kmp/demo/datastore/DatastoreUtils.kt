package com.kmp.demo.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.concurrent.Volatile

object DatastoreUtils {

    @Volatile
    private var INSTANCE: DataStore<Preferences>? = null
    private val mutex = Mutex()
    private suspend fun getInstance(): DataStore<Preferences> {
        // 保证线程安全的懒加载初始化
        val existing = INSTANCE
        if (existing != null) return existing

        return mutex.withLock {
            INSTANCE ?: createDataStore().also { INSTANCE = it }
        }
    }

    suspend fun getStringSuspend(key: String, defaultValue: String): String {
        val stringKey = stringPreferencesKey(key)
        val result = getInstance().data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }.map { preferences ->
                preferences[stringKey] ?: defaultValue
            }
        return result.first()
    }


    suspend fun putStringSuspend(key: String, value: String) {
        getInstance().edit { preferences ->
            val stringKey = stringPreferencesKey(key)
            preferences[stringKey] = value
        }
    }


    suspend fun getIntSuspend(key: String, defaultValue: Int): Int {
        val intKey = intPreferencesKey(key)
        val result = getInstance().data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }.map { preferences ->
                preferences[intKey] ?: defaultValue
            }
        return result.first()
    }

    suspend fun putIntSuspend(key: String, value: Int) {
        getInstance().edit { preferences ->
            val stringKey = intPreferencesKey(key)
            preferences[stringKey] = value
        }
    }

    suspend fun getBoolSuspend(key: String, defaultValue: Boolean): Boolean {
        val booleanKey = booleanPreferencesKey(key)
        val result = getInstance().data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }.map { preferences ->
                preferences[booleanKey] ?: defaultValue
            }
        return result.first()
    }

    suspend fun putBoolSuspend(key: String, value: Boolean) {
        getInstance().edit { preferences ->
            val stringKey = booleanPreferencesKey(key)
            preferences[stringKey] = value
        }
    }

    suspend fun getLongSuspend(key: String, defaultValue: Long): Long {
        val longKey = longPreferencesKey(key)
        val result = getInstance().data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }.map { preferences ->
                preferences[longKey] ?: defaultValue
            }
        return result.first()
    }

    suspend fun putLongSuspend(key: String, value: Long) {
        getInstance().edit { preferences ->
            val stringKey = longPreferencesKey(key)
            preferences[stringKey] = value
        }
    }

    suspend fun getDoubleSuspend(key: String, defaultValue: Double): Double {
        val doubleKey = doublePreferencesKey(key)
        val result = getInstance().data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }.map { preferences ->
                preferences[doubleKey] ?: defaultValue
            }
        return result.first()
    }

    suspend fun putDoubleSuspend(key: String, value: Double) {
        getInstance().edit { preferences ->
            val stringKey = doublePreferencesKey(key)
            preferences[stringKey] = value
        }
    }


    suspend fun getFloatSuspend(key: String, defaultValue: Float): Float {
        val floatKey = floatPreferencesKey(key)
        val result = getInstance().data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }.map { preferences ->
                preferences[floatKey] ?: defaultValue
            }
        return result.first()
    }

    suspend fun putFloatSuspend(key: String, value: Float) {
        getInstance().edit { preferences ->
            val stringKey = floatPreferencesKey(key)
            preferences[stringKey] = value
        }
    }
}