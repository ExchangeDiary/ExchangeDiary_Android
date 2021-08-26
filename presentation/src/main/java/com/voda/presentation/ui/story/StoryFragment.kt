package com.voda.presentation.ui.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.voda.presentation.databinding.FragmentStoryListBinding
import com.voda.presentation.ui.BaseFragment
import com.voda.presentation.util.HorizontalSpaceDecorator
import org.koin.android.viewmodel.ext.android.viewModel

class StoryFragment : BaseFragment() {

    private lateinit var binding: FragmentStoryListBinding
    private val storyVM: StoryViewModel by viewModel()
    private val storyAdapter by lazy { StoryAdapter() }
    private val storyFriendsAdapter by lazy { StoryFriendsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoryListBinding.inflate(inflater, container, false).apply {
            viewModel = storyVM
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setAdapter()
    }

    private fun setAdapter() {
        binding.storyContainer.apply {
            adapter = storyAdapter.apply {
                items = storyVM.getStoryListWithFirstCard()
            }
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }
        binding.friendsContainer.apply {
            adapter = storyFriendsAdapter.apply {
                items = storyVM.getFriends()
                addItemDecoration(HorizontalSpaceDecorator(16f,25f,25f, requireActivity()))
            }
        }
    }

    companion object {
        fun newInstance() = StoryFragment().apply {
            arguments = Bundle().apply {}
        }
    }
}
