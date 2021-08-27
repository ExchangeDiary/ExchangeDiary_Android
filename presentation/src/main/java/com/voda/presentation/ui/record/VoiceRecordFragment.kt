package com.voda.presentation.ui.record

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.voda.presentation.databinding.FragmentVoiceRecordBinding
import com.voda.presentation.ui.record.model.VoiceRecordArg
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


private const val ARG_PARAM = "voice_record_arg"

class VoiceRecordFragment : Fragment() {

    val viewModel: VoiceRecordViewModel by viewModel()
    private lateinit var viewDataBinding: FragmentVoiceRecordBinding

    private val requestPermissions = arrayOf (
        android.Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

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

        })

        viewDataBinding.recordingButton.setOnClickListener {
            viewModel.onRecordButtonClicked()
        }

        viewDataBinding.audioPlayButton.setOnClickListener {
            viewModel.onPlayerButtonClicked()
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