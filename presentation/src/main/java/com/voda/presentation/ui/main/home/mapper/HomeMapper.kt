package com.voda.presentation.ui.main.home.mapper

import com.voda.domain.usecase.home.dto.Home
import com.voda.presentation.R
import com.voda.presentation.ui.main.home.model.*

fun Home.toHomeItems(): List<HomeItem> {

    val result = ArrayList<HomeItem>()

    val header = if (this.DiariesByDDay.isNullOrEmpty()) R.drawable.home_empty_cat else R.drawable.home_cat

    val diaryByDDay = this.DiariesByDDay?.map {
        DiaryByDDayItem(

            //TODO date -> d-day 로 변환
            it.interval.toString(),
            it.title,
            it.bgUrl
        )
    }

    val joinedDiary = this.joinedDiaries?.map {
        JoinedDiaryItem(
            image = it.bgUrl,
            hashTag = it.hashTags?.first()?.name,
            title = it.title,
            userImages = it.members.take(5).map { it.profileImageUrl },
            otherCount = if (it.members.size > 5) it.members.size - 5 else 0
        )
    }

    result.add(HomeItem(HomeItem.ViewType.HEADER, Header(header)))
    if (!diaryByDDay.isNullOrEmpty()) result.add(HomeItem(HomeItem.ViewType.DIARIES_BY_D_DAY, DiaryByDDay(diaryByDDay)))
    if (joinedDiary.isNullOrEmpty()) {
        result.add(HomeItem(HomeItem.ViewType.EMPTY_DIARY, EmptyDiary()))
    } else {
        result.add(HomeItem(HomeItem.ViewType.JOINED_DIARY, JoinedDiary(joinedDiary)))
    }

    return result

}
