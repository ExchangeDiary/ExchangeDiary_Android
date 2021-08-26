package com.voda.presentation.ui.story

import androidx.lifecycle.ViewModel
import com.voda.presentation.ui.signin.model.UserModel
import com.voda.presentation.ui.story.model.StoryModel

class StoryViewModel : ViewModel() {
    fun getStoryListWithFirstCard(): List<StoryModel> {
        //스토리 리스트 get
        val stories: List<StoryModel> = listOf<StoryModel>(
            StoryModel("07/12", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/13", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/14", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/12", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/13", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/14", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/12", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/13", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/14", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/12", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
            StoryModel("07/13", "고양이 좋아", "방구석에서", "아니 글쎄 오늘 무슨 일이 있었느냐면 마리양....", "", "박제리"),
        )
        //현재 작성 차례 이름, 작성중/미작성 여부 get
        val writer = "고영희"
        val isWriting = true

        val writeFlag = if (isWriting) StoryAdapter.WRITING else StoryAdapter.NO_WRITING
        return listOf(StoryModel.createOnlyNameModel(writer, writeFlag)) + stories
    }

    fun getFriends(): List<UserModel> {
        val friends = listOf(
            UserModel("", "", ""),
            UserModel("", "", ""),
            UserModel("", "", ""),
            UserModel("", "", ""),
            UserModel("", "", ""),
            UserModel("", "", ""),
            UserModel("", "", ""),
        )
        return friends
    }
}