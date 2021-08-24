package com.voda.presentation.ui.main.home.binding

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.voda.presentation.R
import com.voda.presentation.ui.main.home.adapter.DiaryHorizontalAdapter
import com.voda.presentation.ui.main.home.adapter.DiaryVerticalAdapter
import com.voda.presentation.ui.main.home.adapter.HomeAdapter
import com.voda.presentation.ui.main.home.adapter.UserProfileAdapter
import com.voda.presentation.ui.main.home.model.DiaryByDDayItem
import com.voda.presentation.ui.main.home.model.HomeItem
import com.voda.presentation.ui.main.home.model.JoinedDiaryItem


@BindingAdapter("bgResource")
fun setHomeItem(imageView: ImageView, resource: Int) {
    imageView.setBackgroundResource(resource)
}

@BindingAdapter("homeItem")
fun setHomeItem(recyclerView: RecyclerView, items: List<HomeItem>?) {
    (recyclerView.adapter as? HomeAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter("homeHorizontalDiaryItem")
fun setHomeHorizontalDiaryItem(recyclerView: RecyclerView, items: List<DiaryByDDayItem>?) {
    (recyclerView.adapter as? DiaryHorizontalAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter("homeVerticalDiaryItem")
fun setHomeVerticalDiaryItem(recyclerView: RecyclerView, items: List<JoinedDiaryItem>?) {
    (recyclerView.adapter as? DiaryVerticalAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter("userItem")
fun setUserItem(recyclerView: RecyclerView, items: List<String>?) {
    (recyclerView.adapter as? UserProfileAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter(value = ["imageProfileUrl"], requireAll = false)
fun setImageProfileUrl(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
//        .load(imageUrl?.toUri())
        .load(R.drawable.ic_profile)
        .circleCrop()
        .error(
            Glide.with(imageView.context).load(R.drawable.ic_profile).circleCrop()
        )
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
}