package com.voda.presentation.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voda.presentation.ui.record.model.VoiceRecordArg

class VoiceRecordViewModel: ViewModel() {

    private val _arg = MutableLiveData<VoiceRecordArg>()
    val arg: LiveData<VoiceRecordArg> = _arg

    fun setArg(voiceRecordArg: VoiceRecordArg){
        _arg.value = voiceRecordArg
    }

}