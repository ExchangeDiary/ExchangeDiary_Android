package com.voda.presentation.ui.main.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.ui.main.home.HomeViewModel
import com.voda.presentation.ui.main.home.adapter.util.DiaryVerticalItemDiffCallback
import com.voda.presentation.ui.main.home.holder.JoinedDiaryItemViewHolder
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.JoinedDiaryItem

class DiaryVerticalAdapter(
    private val viewModel: HomeViewModel,
    private val eventListener: HomeListener
) : ListAdapter<JoinedDiaryItem, RecyclerView.ViewHolder>(DiaryVerticalItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return JoinedDiaryItemViewHolder.from(parent, eventListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = getItem(position)

        if (holder is JoinedDiaryItemViewHolder) {
            holder.bind(info)
        }
    }
}
