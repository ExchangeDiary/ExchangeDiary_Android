package com.voda.presentation.ui.main.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.ui.main.home.adapter.util.UserProfileItemDiffCallback
import com.voda.presentation.ui.main.home.holder.UserProfileViewHolder
import com.voda.presentation.ui.main.home.listener.HomeListener

class UserProfileAdapter(
    private val eventListener: HomeListener
) : ListAdapter<String, RecyclerView.ViewHolder>(UserProfileItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return UserProfileViewHolder.from(parent, eventListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = getItem(position)

        if (holder is UserProfileViewHolder) {
            holder.bind(info)
        }
    }
}
