package com.example.avocado_android.utils.extensions

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences


val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "token_data_store")