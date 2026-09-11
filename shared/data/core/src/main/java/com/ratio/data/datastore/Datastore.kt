package com.ratio.data.datastore

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

typealias RatioDataStore = DataStore<Preferences>

val Context.dataStore: RatioDataStore by preferencesDataStore(
    name = "ratio_datastore_v1"
)

@Composable
fun datastore(): RatioDataStore {
    return LocalContext.current.dataStore
}
