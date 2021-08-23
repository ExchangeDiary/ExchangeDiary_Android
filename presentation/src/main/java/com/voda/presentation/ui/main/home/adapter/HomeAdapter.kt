package com.voda.presentation.ui.main.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.ui.main.home.HomeViewModel
import com.voda.presentation.ui.main.home.adapter.util.HomeItemDiffCallback
import com.voda.presentation.ui.main.home.holder.DiaryByDDayViewHolder
import com.voda.presentation.ui.main.home.holder.HeaderViewHolder
import com.voda.presentation.ui.main.home.holder.JoinedDiaryViewHolder
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.*


class HomeAdapter(
    private val viewModel: HomeViewModel,
    private val eventListener: HomeListener
) : ListAdapter<HomeItem, RecyclerView.ViewHolder>(HomeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            HomeItem.ViewType.HEADER.ordinal -> HeaderViewHolder.from(parent)
            HomeItem.ViewType.DIARIES_BY_D_DAY.ordinal ->  { DiaryByDDayViewHolder.from(parent, viewModel, eventListener)}
            else ->  { JoinedDiaryViewHolder.from(parent, viewModel, eventListener)}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is HeaderViewHolder -> holder.bind(item.item as Header)
                is DiaryByDDayViewHolder -> holder.bind(item.item as DiaryByDDay)
                is JoinedDiaryViewHolder -> holder.bind(item.item as JoinedDiary)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType.ordinal
    }
}
