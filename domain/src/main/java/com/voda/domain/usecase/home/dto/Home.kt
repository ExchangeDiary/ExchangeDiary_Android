package com.voda.domain.usecase.home.dto

import com.voda.domain.entity.Diary
import com.voda.domain.entity.User

data class Home(
    val user: User,
    val DiariesByDDay: List<Diary>?,
    val joinedDiaries: List<Diary>?
)
