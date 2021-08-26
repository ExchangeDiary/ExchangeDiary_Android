package com.voda.presentation.ui.main.home.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.voda.presentation.ui.main.home.model.JoinedDiaryItem

class DiaryVerticalItemDiffCallback : DiffUtil.ItemCallback<JoinedDiaryItem>() {

    override fun areItemsTheSame(oldItem: JoinedDiaryItem, newItem: JoinedDiaryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JoinedDiaryItem, newItem: JoinedDiaryItem): Boolean {
        return oldItem == newItem
    }
}
