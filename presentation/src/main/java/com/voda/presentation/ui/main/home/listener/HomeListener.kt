package com.voda.presentation.ui.main.home.listener

import com.voda.presentation.ui.main.home.model.DiaryByDDayItem
import com.voda.presentation.ui.main.home.model.JoinedDiaryItem

interface HomeListener {

    fun onDiaryClicked(item: DiaryByDDayItem)

    fun onDiaryClicked(item: JoinedDiaryItem)

}