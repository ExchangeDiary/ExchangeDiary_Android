package com.voda.presentation.ui.main.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.voda.presentation.databinding.FragmentBottomNavBinding
import com.voda.presentation.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class BottomNavFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentBottomNavBinding

    private val viewModel: BottomNavViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentBottomNavBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            BottomNavFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}