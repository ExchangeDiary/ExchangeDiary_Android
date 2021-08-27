package com.voda.presentation.ui.record

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.voda.presentation.databinding.FragmentVoiceRecordBinding
import com.voda.presentation.ui.record.model.EffectMode
import com.voda.presentation.ui.record.model.PlayerState
import com.voda.presentation.ui.record.model.VoiceRecordArg
import com.voda.presentation.ui.record.util.TimeUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*
import kotlin.concurrent.timer


private const val ARG_PARAM = "voice_record_arg"

class VoiceRecordFragment : Fragment() {

    val viewModel: VoiceRecordViewModel by viewModel()
    private lateinit var viewDataBinding: FragmentVoiceRecordBinding

    private val requestPermissions = arrayOf (
        android.Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    var timer: Timer? = null

    var recordTimer: Timer? = null

    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireNotNull(arguments).apply {
            (this.getSerializable(ARG_PARAM) as? VoiceRecordArg)?.run {
                viewModel.setArg(this)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentVoiceRecordBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLifecycleOwner()
        requestAudioPermission()

        setupEvents()
    }


    private fun setupEvents() {

        viewModel.isPlayerState.observe(this.viewLifecycleOwner, Observer {
            when(it) {
                PlayerState.Play -> setSeekbar()
                PlayerState.Playing -> updateSeekbar()
                PlayerState.Recording -> recordingDuration()
            }
        })

        viewModel.effect.observe(this.viewLifecycleOwner, Observer {
            viewModel.player?.let { setSeekbar() }
        })

        viewDataBinding.recordingButton.setOnClickListener {
            viewModel.onRecordButtonClicked()
        }

        viewDataBinding.audioPlayButton.setOnClickListener {
            viewModel.onPlayerButtonClicked()

        }

        viewDataBinding.thickEffectButton.setOnClickListener {
            viewModel.setEffectMode(EffectMode.Thick)
        }

        viewDataBinding.thinEffectButton.setOnClickListener {
            viewModel.setEffectMode(EffectMode.Thin)
        }

        viewDataBinding.noEffectButton.setOnClickListener {
            viewModel.setEffectMode(EffectMode.No_effect)
        }

        viewDataBinding.previousButton.setOnClickListener {

        }

        viewDataBinding.afterButton.setOnClickListener {

        }

        viewDataBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
//                Timber.i("progress $progress / player ${viewModel.player!!.duration}")
//                if(viewModel.player != null && progress >= viewModel.player!!.duration){
//                    timer?.cancel()
//                    viewModel.setPlayerState(PlayerState.Play)
//                }
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
        })
    }
    var time = 0

    private fun recordingDuration() {
        recordTimer = timer(period = 10) {
            time++
            Timber.i("timer $time")
            uiScope.launch {
                viewDataBinding.fileLength.text = TimeUtils.getRecordTimerText(time).also { Timber.i("$it") }

            }
        }
    }

    private fun stopRecordTimer() {

    }

    private fun updateSeekbar() {
        viewModel.player?.let { player ->
            timer = timer(period = 100, initialDelay = 100) {
                val totalDuration = player.duration
                val currentDuration = player.currentPosition

                uiScope.launch {
                    viewDataBinding.currentDuration.text = TimeUtils.milliSecondsToTimer(currentDuration).also { Timber.i(it)}
                    viewDataBinding.remainingTime.text = TimeUtils.milliSecondsToTimer(totalDuration - currentDuration).also { Timber.i(it)}

                    viewDataBinding.seekBar.progress = currentDuration.also { Timber.i(it.toString())}
                }
            }
        }
    }

    private fun setSeekbar(){
        recordTimer?.cancel()
        timer?.cancel()
        viewModel.player?.let { player->
            viewDataBinding.seekBar.apply {
                progress = 0
                max = player.duration
            }
        }
    }


    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun requestAudioPermission() {
        requestPermissions(requestPermissions, REQUEST_RECORD_AUDIO_PERMISSION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRequestPermissionGranted  =
            requestCode == REQUEST_RECORD_AUDIO_PERMISSION &&
                    grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (!audioRequestPermissionGranted) {
            Timber.i("NOT GRANTED")
        }
    }

    companion object {

        private const val REQUEST_RECORD_AUDIO_PERMISSION = 100

        @JvmStatic
        fun newInstance(arg: VoiceRecordArg) =
            VoiceRecordFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM, arg)
                }
            }
    }
}