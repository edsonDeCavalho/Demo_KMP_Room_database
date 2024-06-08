package com.example.kmp_database

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kmp_database.database.getKmp_database

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = getKmp_database(applicationContext)

        setContent {
            App(database)
        }
    }
}