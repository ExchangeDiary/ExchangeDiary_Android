package com.voda.presentation.ui.main.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.databinding.HomeHorizontalDiaryLayoutBinding
import com.voda.presentation.ui.main.home.HomeViewModel
import com.voda.presentation.ui.main.home.adapter.DiaryHorizontalAdapter
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.DiaryByDDay

class DiaryByDDayViewHolder constructor(val binding: HomeHorizontalDiaryLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DiaryByDDay) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, viewModel: HomeViewModel, eventListener: HomeListener): DiaryByDDayViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeHorizontalDiaryLayoutBinding.inflate(layoutInflater, parent, false)
            binding.horizontalRecyclerview.adapter = DiaryHorizontalAdapter(viewModel, eventListener)

            return DiaryByDDayViewHolder(binding)
        }
    }
}