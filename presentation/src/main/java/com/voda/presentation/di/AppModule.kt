package com.voda.presentation.di

import com.voda.presentation.ui.main.home.HomeViewModel
import com.voda.presentation.ui.main.nav.BottomNavViewModel
import com.voda.presentation.ui.story.StoryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { BottomNavViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { StoryViewModel() }

}