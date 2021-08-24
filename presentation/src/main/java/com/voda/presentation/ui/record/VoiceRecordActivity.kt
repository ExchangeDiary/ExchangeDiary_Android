package com.voda.presentation.ui.record

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.voda.presentation.R
import com.voda.presentation.ui.main.nav.BottomNavFragment

class VoiceRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_record)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BottomNavFragment.newInstance())
                .commitNow()
        }
    }
}