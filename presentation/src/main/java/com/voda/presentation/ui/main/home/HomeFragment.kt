package com.voda.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.voda.presentation.R
import com.voda.presentation.databinding.FragmentBottomNavBinding
import com.voda.presentation.databinding.FragmentHomeBinding
import com.voda.presentation.ui.BaseFragment
import com.voda.presentation.ui.main.home.adapter.HomeAdapter
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.DiaryByDDayItem
import com.voda.presentation.ui.main.home.model.JoinedDiaryItem
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), HomeListener {

    private lateinit var viewDataBinding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLifecycleOwner()
        setupAdapter()
        viewModel.load()
    }

    private fun setupAdapter() {
        viewDataBinding.homeRecyclerview.apply {
            adapter = HomeAdapter(viewModel, this@HomeFragment)
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onDiaryClicked(item: DiaryByDDayItem) {

    }

    override fun onDiaryClicked(item: JoinedDiaryItem) {
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}