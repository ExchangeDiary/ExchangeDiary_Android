package com.voda.presentation.ui.main.home.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.voda.presentation.ui.main.home.model.DiaryByDDayItem

class DiaryHorizontalItemDiffCallback : DiffUtil.ItemCallback<DiaryByDDayItem>() {

    override fun areItemsTheSame(oldItem: DiaryByDDayItem, newItem: DiaryByDDayItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DiaryByDDayItem, newItem: DiaryByDDayItem): Boolean {
        return oldItem == newItem
    }
}
