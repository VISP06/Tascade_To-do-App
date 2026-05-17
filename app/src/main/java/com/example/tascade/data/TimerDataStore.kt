package com.example.tascade.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "timer_preferences")

class TimerDataStore (private val context: Context){
    companion object{
        val TARGET_END_TIME = longPreferencesKey("target_end_time")
        val IS_WORK_SESSION = booleanPreferencesKey("is_work_session")
    }
}