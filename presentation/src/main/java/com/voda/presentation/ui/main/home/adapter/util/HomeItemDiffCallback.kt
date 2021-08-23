package com.voda.presentation.ui.main.home.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.voda.presentation.ui.main.home.model.HomeItem

class HomeItemDiffCallback : DiffUtil.ItemCallback<HomeItem>() {

    override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.viewType == newItem.viewType
    }

    override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem == newItem
    }
}
