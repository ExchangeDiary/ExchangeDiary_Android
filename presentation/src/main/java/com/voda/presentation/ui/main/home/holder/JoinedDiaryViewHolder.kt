package com.voda.presentation.ui.main.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.databinding.HomeVerticalDiaryLayoutBinding
import com.voda.presentation.ui.main.home.HomeViewModel
import com.voda.presentation.ui.main.home.adapter.DiaryVerticalAdapter
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.JoinedDiary

class JoinedDiaryViewHolder constructor(val binding: HomeVerticalDiaryLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: JoinedDiary) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, viewModel: HomeViewModel, eventListener: HomeListener): JoinedDiaryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeVerticalDiaryLayoutBinding.inflate(layoutInflater, parent, false)
            binding.recyclerview.adapter = DiaryVerticalAdapter(viewModel, eventListener)

            return JoinedDiaryViewHolder(binding)
        }
    }
}