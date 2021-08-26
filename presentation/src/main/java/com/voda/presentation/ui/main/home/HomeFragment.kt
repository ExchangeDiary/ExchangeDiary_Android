package com.voda.presentation.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.databinding.FragmentHomeBinding
import com.voda.presentation.ui.BaseFragment
import com.voda.presentation.ui.main.home.adapter.HomeAdapter
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.DiaryByDDayItem
import com.voda.presentation.ui.main.home.model.JoinedDiaryItem
import com.voda.presentation.ui.story.StoryActivity
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
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        setupListener()
        viewModel.load()
    }

    private fun setupAdapter() {
        viewDataBinding.homeRecyclerview.apply {
            adapter = HomeAdapter(viewModel, this@HomeFragment)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    setToolbarBarElevation(scrollY)
                }
            })
        }
    }

    private fun setupListener(){
        //TODO sangeun : 임시 버튼
        viewDataBinding.toolbarTitle.setOnClickListener {
            activity?.let{
                val intent = Intent(context, StoryActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setToolbarBarElevation(scrollY: Int) {
        if(scrollY == 0 && viewModel.isScrollTop.value != true) {
            viewModel.setScrollTop(true)
        } else if (scrollY != 0 && viewModel.isScrollTop.value != false) {
            viewModel.setScrollTop(false)
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