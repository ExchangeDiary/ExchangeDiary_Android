package com.voda.presentation.ui.main.nav.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.voda.presentation.databinding.CustomBottomTabBinding
import com.voda.presentation.util.BottomTab

class BottomTabLayout(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    val binding: CustomBottomTabBinding =
        CustomBottomTabBinding.inflate(LayoutInflater.from(context), this, true)
    private lateinit var viewPager: ViewPager2

    init {
        setupEvents()
    }

    fun setupWithViewPager(viewPager: ViewPager2) {
        this.viewPager = viewPager
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when(position) {
                    BottomTab.Notification.pos -> setButtons(BottomTab.Notification)
                    BottomTab.Home.pos -> setButtons(BottomTab.Home)
                    BottomTab.MyPage.pos -> setButtons(BottomTab.MyPage)
                }
            }
        })
    }

    private fun setupEvents(){
        binding.notificationButton.setOnClickListener {
            if (viewPager.currentItem != BottomTab.Notification.pos) {
                viewPager.currentItem = BottomTab.Notification.pos
            }
        }
        binding.homeButton.setOnClickListener {
            if (viewPager.currentItem != BottomTab.Home.pos) {
                viewPager.currentItem = BottomTab.Home.pos
            }
        }

        binding.myPageButton.setOnClickListener {
            if (viewPager.currentItem != BottomTab.MyPage.pos) {
                viewPager.currentItem = BottomTab.MyPage.pos
            }
        }
    }

    private fun setButtons(buttonToActivate: BottomTab){
        when(buttonToActivate){
            BottomTab.Notification -> {
                binding.notificationButton.activate()
                binding.homeButton.inActivate()
                binding.myPageButton.inActivate()

            }
            BottomTab.Home -> {
                binding.notificationButton.inActivate()
                binding.homeButton.activate()
                binding.myPageButton.inActivate()

            }
            BottomTab.MyPage -> {
                binding.notificationButton.inActivate()
                binding.homeButton.inActivate()
                binding.myPageButton.activate()

            }
        }

    }
}