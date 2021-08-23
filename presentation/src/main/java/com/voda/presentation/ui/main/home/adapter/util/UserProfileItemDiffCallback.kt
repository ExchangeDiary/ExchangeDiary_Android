package com.voda.presentation.ui.main.home.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.voda.presentation.ui.main.home.model.DiaryByDDayItem

class UserProfileItemDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
