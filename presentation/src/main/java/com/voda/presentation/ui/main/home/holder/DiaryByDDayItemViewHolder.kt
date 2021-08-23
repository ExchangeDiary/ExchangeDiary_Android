package com.voda.presentation.ui.main.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.databinding.HomeHorizontalDiaryItemBinding
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.DiaryByDDayItem

class DiaryByDDayItemViewHolder constructor(val binding: HomeHorizontalDiaryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DiaryByDDayItem) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: HomeListener?): DiaryByDDayItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeHorizontalDiaryItemBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener

            return DiaryByDDayItemViewHolder(binding)
        }
    }
}