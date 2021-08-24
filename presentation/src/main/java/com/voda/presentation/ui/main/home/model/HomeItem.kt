package com.voda.presentation.ui.main.home.model

data class HomeItem(
    val viewType: ViewType,
    val item: HomeInfo
) {
    enum class ViewType {
        HEADER,
        DIARIES_BY_D_DAY,
        EMPTY_DIARY,
        JOINED_DIARY
    }
}
