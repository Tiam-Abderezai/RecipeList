package com.example.recipelist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.recipelist.R
import com.example.recipelist.utils.Globals
import com.example.recipelist.utils.Globals.Companion.TAG_ACT_MAIN
import com.example.recipelist.utils.Logger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Log.d(TAG_ACT_MAIN, "onCreate: ")
//        Logger.logd(TAG_ACT_MAIN, "onCreate")
    }
}