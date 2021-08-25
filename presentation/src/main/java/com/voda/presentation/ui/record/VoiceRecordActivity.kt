package com.voda.presentation.ui.record

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.voda.presentation.R
import com.voda.presentation.ui.main.nav.BottomNavFragment
import com.voda.presentation.ui.record.model.VoiceRecordArg

class VoiceRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_record)

        val arg = getArg(intent)

        if (savedInstanceState == null && arg != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VoiceRecordFragment.newInstance(arg))
                .commitNow()
        }
    }

    private fun getArg(intent: Intent): VoiceRecordArg? {
        return intent.getSerializableExtra(ARG) as? VoiceRecordArg
    }

    companion object {
        private const val ARG = "voice_record_arg"

        fun starterIntent(context: Context, voiceRecordArg: VoiceRecordArg): Intent {
            return Intent(context, VoiceRecordActivity::class.java).apply {
                putExtra(ARG, voiceRecordArg)
            }
        }
    }
}