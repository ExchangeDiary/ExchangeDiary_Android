package com.voda.presentation.ui.main.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.ui.main.home.HomeViewModel
import com.voda.presentation.ui.main.home.adapter.util.DiaryHorizontalItemDiffCallback
import com.voda.presentation.ui.main.home.holder.DiaryByDDayItemViewHolder
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.DiaryByDDayItem

class DiaryHorizontalAdapter(
    private val viewModel: HomeViewModel,
    private val eventListener: HomeListener
) : ListAdapter<DiaryByDDayItem, RecyclerView.ViewHolder>(DiaryHorizontalItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DiaryByDDayItemViewHolder.from(parent, eventListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = getItem(position)

        if (holder is DiaryByDDayItemViewHolder) {
            holder.bind(info)
        }
    }
}
