package com.voda.presentation.ui.main.nav

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BottomNavPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val fragmentMaps: SparseArray<Fragment>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragmentMaps.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentMaps[position]
    }

}