package com.voda.presentation.ui.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.voda.presentation.R
import com.voda.presentation.databinding.ItemStoryFriendsCircleBinding
import com.voda.presentation.ui.signin.model.UserModel

class StoryFriendsAdapter : RecyclerView.Adapter<StoryFriendsAdapter.StoryFriendsViewHolder>() {
    var items: List<UserModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryFriendsViewHolder {
        return StoryFriendsViewHolder(
            ItemStoryFriendsCircleBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: StoryFriendsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class StoryFriendsViewHolder(
        private val binding: ItemStoryFriendsCircleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserModel) {
            binding.image.run {
                Glide.with(this.context).load(R.drawable.me).circleCrop().into(this)
            }
        }
    }
}