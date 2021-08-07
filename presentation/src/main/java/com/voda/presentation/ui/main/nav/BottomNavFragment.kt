package com.voda.presentation.ui.main.nav

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.voda.presentation.databinding.FragmentBottomNavBinding
import com.voda.presentation.ui.BaseFragment
import com.voda.presentation.ui.main.home.HomeFragment
import com.voda.presentation.ui.main.mypage.MyPageFragment
import com.voda.presentation.ui.main.notification.NotificationFragment
import com.voda.presentation.util.BottomTab
import org.koin.android.viewmodel.ext.android.viewModel

class BottomNavFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentBottomNavBinding

    private val viewModel: BottomNavViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentBottomNavBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLifecycleOwner()

        setupBottomTab()
        setupViewPager()
    }

    private fun setupViewPager() {

        SparseArray<Fragment>().apply {
            put(BottomTab.Notification.pos, NotificationFragment.newInstance())
            put(BottomTab.Home.pos, HomeFragment.newInstance())
            put(BottomTab.MyPage.pos, MyPageFragment.newInstance())
        }.run {
            activity?.let { activity ->
                viewDataBinding.viewPager.adapter = BottomNavPagerAdapter(activity, this)
            }
        }
    }

    private fun setupBottomTab() {
        viewDataBinding.bottomTab.apply {
            setupWithViewPager(viewDataBinding.viewPager)
        }
    }

    private fun setupLifecycleOwner() {
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            BottomNavFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}