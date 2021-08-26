package com.voda.presentation.ui.main.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.databinding.HomeHeaderLayoutBinding
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.Header

class HeaderViewHolder constructor(val binding: HomeHeaderLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Header) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeHeaderLayoutBinding.inflate(layoutInflater, parent, false)

            return HeaderViewHolder(binding)
        }
    }
}