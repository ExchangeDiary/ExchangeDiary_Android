package com.voda.presentation.ui.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.voda.presentation.R
import com.voda.presentation.databinding.ItemStoryCardBinding
import com.voda.presentation.databinding.ItemStoryFirstCardBinding
import com.voda.presentation.ui.story.model.StoryModel

class StoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<StoryModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FIRST_CARD) {
            FirstCardViewHolder(ItemStoryFirstCardBinding.inflate(LayoutInflater.from(parent.context)))
        } else {
            StoryViewHolder(ItemStoryCardBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StoryViewHolder) {
            holder.bind(items[position])
        } else {
            (holder as FirstCardViewHolder).bind(items[position])
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) FIRST_CARD else STORY_CARD
    }

    class StoryViewHolder(
        private val binding: ItemStoryCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: StoryModel) {
            binding.model = model
        }
    }

    class FirstCardViewHolder(
        private val binding: ItemStoryFirstCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: StoryModel) {
            binding.writer = model.name
            if (model.text == WRITING) {
                binding.image.setImageResource(R.drawable.slice_42)
                binding.des1.text = "님이"
                binding.des2.text = "이야기를 쓰고있어요!"
            } else {
                binding.image.setImageResource(R.drawable.post)
                binding.container.setBackgroundColor(
                    ContextCompat.getColor(binding.container.context, R.color.background_blue)
                )
                ContextCompat.getColor(binding.container.context, R.color.white).run {
                    binding.name.setTextColor(this)
                    binding.des1.setTextColor(this)
                    binding.des2.setTextColor(this)
                }
                binding.des1.text = "님의"
                binding.des2.text = "이야기를 나눠주세요!"
            }
        }
    }

    companion object {
        const val FIRST_CARD = 0
        const val STORY_CARD = 1
        const val WRITING = "w"
        const val NO_WRITING = "n"
    }
}