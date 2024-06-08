package com.example.kmp_database

import android.app.Application
import co.touchlab.kermit.Logger

class kmp_database : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.d("kmp_databaseApplication")
    }
}