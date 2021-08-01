package com.voda.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.voda.presentation.R
import com.voda.presentation.ui.main.nav.BottomNavFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BottomNavFragment.newInstance())
                .commitNow()
        }
    }
}