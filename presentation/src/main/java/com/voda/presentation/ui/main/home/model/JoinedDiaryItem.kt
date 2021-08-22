package com.voda.presentation.ui.main.home.model

data class JoinedDiaryItem(
    val image: String,
    val hashTag: String?,
    val title: String,
    val userImages: List<String>,
    val otherCount: Int
)
