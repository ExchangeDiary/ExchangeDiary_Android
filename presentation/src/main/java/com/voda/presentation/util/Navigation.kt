package com.voda.presentation.util

import android.app.Activity
import com.voda.presentation.ui.record.VoiceRecordActivity
import com.voda.presentation.ui.record.model.VoiceRecordArg

object Navigation {

    fun openVoiceRecordingPage(activity: Activity, voiceRecordArg: VoiceRecordArg) {
        activity.startActivityForResult(
            VoiceRecordActivity.starterIntent(activity, voiceRecordArg),
            100
        )
    }

}