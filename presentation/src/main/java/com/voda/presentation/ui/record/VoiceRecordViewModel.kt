package com.voda.presentation.ui.record

import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voda.presentation.ui.record.model.EffectMode
import com.voda.presentation.ui.record.model.PlayerState
import com.voda.presentation.ui.record.model.VoiceRecordArg
import timber.log.Timber
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class VoiceRecordViewModel: ViewModel() {

    private val _arg = MutableLiveData<VoiceRecordArg>()
    val arg: LiveData<VoiceRecordArg> = _arg

    private val _isPlayerState = MutableLiveData<PlayerState>().apply { value = PlayerState.Record }
    val isPlayerState: LiveData<PlayerState> = _isPlayerState

    private val _effect = MutableLiveData<EffectMode>().apply { value = EffectMode.No_effect }
    val effect: LiveData<EffectMode> = _effect

    private val _currentDuration = MutableLiveData<String>()
    val currentDuration: LiveData<String> = _currentDuration

    private val _remainingTime = MutableLiveData<String>()
    val remainingTime: LiveData<String> = _remainingTime

    lateinit var recorder: MediaRecorder

    var player: MediaPlayer? = null


    val filePath: String = makeFileName()

    fun setArg(voiceRecordArg: VoiceRecordArg){
        _arg.value = voiceRecordArg
    }

    fun setPlayerState(playerState: PlayerState){
        _isPlayerState.value = playerState
    }

    fun setEffectMode(effectMode: EffectMode){
        _effect.value = effectMode
        setUpAudio()
    }

    init {
//        setupRecord()
    }

    fun onRecordButtonClicked() {
        isPlayerState.value?.let {
            when(it) {
                PlayerState.Record -> startRecording()
                PlayerState.Recording -> stopRecording()
                else -> {}// 팝업 띄우기
            }
        }
    }

    fun onPlayerButtonClicked() {
        isPlayerState.value?.let {
            when(it) {
                PlayerState.Play, PlayerState.Pause -> playAudio()
                PlayerState.Playing -> pausePlayer()
                else -> {} // nothing
            }
        }
    }

    fun startRecording() {
        setPlayerState(PlayerState.Recording)
        setupRecord()
        recorder.start()
//        stopVisualizer()
    }

    fun stopRecording() {
        recorder.stop()
        recorder.release()

//        setupRecord()
        setUpAudio()
        setPlayerState(PlayerState.Play)
    }

    fun setUpAudio() {
        player = MediaPlayer().apply {
            Timber.i("$filePath")
            setDataSource(filePath)
            playbackParams = this.playbackParams.apply {
                speed = 1f
                pitch = getPitchValue()
            }
            this.playbackParams.pitch
            prepare()
            setOnCompletionListener {
                setPlayerState(PlayerState.Play)
            }
        }
    }

    fun playAudio() {
        setPlayerState(PlayerState.Playing)
        player?.start()
//        player = MediaPlayer().apply {
//            Timber.i("$filePath")
//            setDataSource(filePath)
//            playbackParams = this.playbackParams.apply {
//                speed = 1f
//                pitch = getPitchValue()
//            }
//            this.playbackParams.pitch
//            prepare()
//            start()
//        }
    }

    fun pausePlayer() {
        setPlayerState(PlayerState.Pause)
        player?.pause()
    }

    private fun getPitchValue(): Float {
        effect.value?.let {
            return when(it) {
                EffectMode.Thick -> 0.7f
                EffectMode.Thin -> 2f
                else -> 1f
            }
        }
        return 1f
    }

    //TODO 서버에서 url 로 받아올경우
    private fun playAudioForUrl(speed: Float) {
        val mediaPlayer = MediaPlayer()
        val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.playbackParams = mediaPlayer.playbackParams.apply {
                setSpeed(speed)
                pitch = 2.5f
            }
            mediaPlayer.playbackParams.pitch
            mediaPlayer.prepare()
            mediaPlayer.start()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun setupRecord() {
        recorder = MediaRecorder()
            .apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(filePath)
            }
        recorder.prepare()
    }

    private fun makeFileName(): String{
        Environment.getExternalStorageDirectory().run {
            val childName = "Untitle_"+ getDateFormat() +".3gp"
            File(this, "recorded.3gp")
        }.run {
            val fileName = this.absolutePath
            Timber.i("저장할 파일 명 : $fileName")
            return fileName
        }
    }

    private fun getDateFormat(): String{
        return try {
            Calendar.getInstance()
            SimpleDateFormat("yy/MM/dd", Locale.KOREA).format(Calendar.getInstance().time)
        } catch (e: Exception) {
            e.printStackTrace()
            System.currentTimeMillis().toString()
        }
    }
}