package com.voda.presentation.ui.main.home.holder

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.databinding.HomeVerticalDiaryItemBinding
import com.voda.presentation.ui.main.home.adapter.UserProfileAdapter
import com.voda.presentation.ui.main.home.listener.HomeListener
import com.voda.presentation.ui.main.home.model.JoinedDiaryItem
import com.voda.presentation.util.CommonUtil

class JoinedDiaryItemViewHolder constructor(val binding: HomeVerticalDiaryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: JoinedDiaryItem) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: HomeListener): JoinedDiaryItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeVerticalDiaryItemBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener
            binding.userImagesRecyclerView.apply {
                adapter = UserProfileAdapter(eventListener)
                addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        val position = parent.getChildAdapterPosition(view)
                        if (position != 0) outRect.left = CommonUtil.dpToPx( 8) * -1
                    }
                })
            }

            return JoinedDiaryItemViewHolder(binding)
        }
    }
}