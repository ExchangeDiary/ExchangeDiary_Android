package com.voda.presentation.ui.main.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.databinding.HomeVerticalDiaryItemBinding
import com.voda.presentation.databinding.UserImageItemBinding
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.JoinedDiaryItem

class UserProfileViewHolder constructor(val binding: UserImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: HomeListener?): UserProfileViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = UserImageItemBinding.inflate(layoutInflater, parent, false)

            return UserProfileViewHolder(binding)
        }
    }
}