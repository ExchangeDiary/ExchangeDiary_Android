package com.voda.domain.usecase.home.dto

import com.voda.domain.entity.Diary

data class Home(
    val DiariesByDDay: List<Diary>?,
    val joinedDiaries: List<Diary>?
)
