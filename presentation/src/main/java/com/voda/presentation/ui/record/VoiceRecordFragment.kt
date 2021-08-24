package com.voda.presentation.ui.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.voda.presentation.R
import com.voda.presentation.databinding.FragmentVoiceRecordBinding
import com.voda.presentation.ui.record.model.VoiceRecordArg
import org.koin.android.viewmodel.ext.android.viewModel


private const val ARG_PARAM = "voice_record_arg"

class VoiceRecordFragment : Fragment() {

    val viewModel: VoiceRecordViewModel by viewModel()
    private lateinit var viewDataBinding: FragmentVoiceRecordBinding

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

    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    companion object {

        @JvmStatic
        fun newInstance(arg: VoiceRecordArg) =
            VoiceRecordFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM, arg)
                }
            }
    }
}