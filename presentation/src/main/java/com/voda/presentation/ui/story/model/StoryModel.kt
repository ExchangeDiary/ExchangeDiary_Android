package com.voda.presentation.ui.story.model

class StoryModel(
    val date: String,
    val title: String,
    val place: String,
    val text: String,
    val imageUrl: String,
    var name: String,
) {
    companion object {
        fun createOnlyNameModel(name: String, writeFlag: String): StoryModel {
            return StoryModel("", "", "", writeFlag, "", name)
        }
    }
}
