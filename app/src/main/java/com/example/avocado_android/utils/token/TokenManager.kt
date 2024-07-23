package com.example.avocado_android.utils.token

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.avocado_android.utils.extensions.datastore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val COOKIE = stringPreferencesKey("cookie")
    }

    private val dataStore: DataStore<Preferences> = context.datastore

    fun saveToken(token: String) = runBlocking{
        dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = token
        }
    }
    fun saveCookie(token: String) = runBlocking{
        dataStore.edit { prefs ->
            prefs[COOKIE] = token
        }
    }
    fun getAccessToken(): Flow<String?> {
        return dataStore.data.map { prefs ->
            prefs[ACCESS_TOKEN_KEY]
        }
    }
    fun getCookie(): Flow<String?> {
        return dataStore.data.map { prefs ->
            prefs[COOKIE]
        }
    }
    suspend fun deleteAccessToken(){
        dataStore.edit { prefs ->
            prefs.remove(ACCESS_TOKEN_KEY)
        }
    }

}